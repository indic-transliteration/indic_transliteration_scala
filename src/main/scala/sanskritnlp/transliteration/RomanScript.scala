package sanskritnlp.transliteration

import java.util.Collections

import org.slf4j.{Logger, LoggerFactory}

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

// Point of entry: toDevanagari()
// Read that function, and the logic will be clear.

trait RomanScript extends IndicScript {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)
  val romanToDevaIndependentVowels: Map[String, String] = null

  val romanToDevaConsonants: Map[String, String] = null
  val romanToDevaConsonantsNoVirama: Map[String, String] = null
  val romanToDevaContextFreeReplacements: Map[String, String] = null

  val devaIndependentVowelsToRoman: Map[String, String] = null
  val devaConsonantsToRoman: Map[String, String] = null
  val devaToRomanGeneral: Map[String, String] = null
  val distinctCharacters: List[String] = null

  val caseNeutral = false

  val devaIndependentToDependent: Map[String, String] = Map(
    "अ" -> "",
    "आ" -> "ा",
    "इ" -> "ि",
    "ई" -> "ी",
    "उ" -> "ु", "ऊ" -> "ू",
    "ऋ" -> "ृ", "ॠ" -> "ॄ",
    "ऌ" -> "ॢ", "ॡ" -> "ॣ",
    "ए" -> "े",
    "ऐ" ->  "ै",
    "ओ" -> "ो",  "औ" -> "ौ")

  def debugString(): Unit = {
    println(romanToDevaIndependentVowels)
    println(romanToDevaConsonants)
    println(romanToDevaConsonantsNoVirama)
    println(romanToDevaContextFreeReplacements)
    println(devaToRomanGeneral)
  }

  def replaceKeysLongestFirst(str_in: String, mapping: Map[String, String]): String = {

    def makeRegexFromKeys(keys: Iterable[String]): Regex = {
      def escapeWildcardsForRegex(input: String): String = {
        input.replaceAllLiterally(".", "\\.").replaceAllLiterally("|", "\\|")
      }
      ("(" + keys.map(escapeWildcardsForRegex).mkString("|") + ")").r
    }
    var output = str_in
    val keyLengths = mapping.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.
    keyLengths.foreach(x => {
      val mapping_length_x = mapping.filter(t => (t._1.length() == x))
//      log.info(mapping_length_x.toString())
      val regexFromKeys = makeRegexFromKeys(mapping_length_x.keys)
      output = regexFromKeys.replaceAllIn(output, _ match { case regexFromKeys(key) => mapping(key) })
    })
    output
  }

  override def toDevanagari(str_in: String): String = {
    def replaceViraamaFollowedByIndependentVowels(str_in: String) = {
      val viraamaPlusDevaIndependentToDependentMap = devaIndependentToDependent.map({case (key: String, value: String) => ("्" + key, value)})
      var output = str_in
      viraamaPlusDevaIndependentToDependentMap.foreach({case (key: String, value: String) => output = output.replace(key, value)})
      output
    }

    var output = str_in
    if (caseNeutral) {
      output = output.toLowerCase
    }
    try {
      // Consider RR in HK scheme. This can be a dependent vowel (when it follows a consonant), or an independent vowel otherwise.
      //
      // One can do the following in sequence:
      // replaceRomanDependentVowels(output) ->
      // replaceRomanConsonantsWhichAreFollowedByVowels(output)
      // [Such consonants would need special treatment: ke would be  के rather than क्े.]
      // -> replaceKeysLongestFirst(output, romanToDevaConsonants ++ romanToDevaContextFreeReplacements ++ romanToDevaIndependentVowels)
      //
      // However, it would mess up corner cases like turning lRR in HK to लॄ rather than ॡ.
      //
      // Instead we do the below:
      output = replaceKeysLongestFirst(output, romanToDevaConsonants ++ romanToDevaContextFreeReplacements ++ romanToDevaIndependentVowels)
      output = replaceViraamaFollowedByIndependentVowels(output)
      output
    } catch {
      case e: java.util.NoSuchElementException => {
        str_in
      }
    }
  }

  def replaceDependentVowelsWithIndepedentVowels(str_in: String): String = {
    // In doing so, we add a few virAma-s. So, ग्रा becomes ग्र्आ. This will make devanAgarI consonants uniform as far as replacement with roman glyphs is concerned.
    var output = str_in

    val VIRAMA = "्"
    val DEVANAGARI_CONSONANTS_WITHOUT_VIRAMA_REGEX = "[क-हक़-य़\u0978-ॿ]"
    val consonantVowelPattern = (
      s"($DEVANAGARI_CONSONANTS_WITHOUT_VIRAMA_REGEX)"
      + "([ा-ौॎ-ॏ])").r
    output = consonantVowelPattern.replaceAllIn(output, _ match { case consonantVowelPattern(consonant, vowel) =>
      consonant + VIRAMA + devaIndependentToDependent.map(_.swap).getOrElse(vowel, vowel)})

    // Complete the job - add a virAma so that ग्र becomes ग्र्अ.
    val consonantNonVowelPattern = (
      s"($DEVANAGARI_CONSONANTS_WITHOUT_VIRAMA_REGEX)"
        + s"(?!$VIRAMA)").r
    // log.info(consonantNonVowelPattern)
    output = consonantNonVowelPattern.replaceAllIn(output, (m:Match) => {m.group(0) + VIRAMA + "अ"})
    // consonantNonVowelPattern has a problem - it wouldn't match a terminal consonant. Hence the below.
    if(!output.isEmpty && romanToDevaConsonantsNoVirama.values.toList.contains(output.last.toString)) {
      output = output + VIRAMA + "अ"
    }
    output
  }

  def isEncoding(str_in: String): Boolean = {
    distinctCharacters.map(x => str_in.contains(x)).contains(true)
  }

  override def fromDevanagari(str_in: String): String = {
    var output = str_in
    // Here too we need to prefer longest sequences first.
    output = replaceDependentVowelsWithIndepedentVowels(output)
//     log.info("replaceDependentVowelsWithIndepedentVowels : " + output)

    val replacementMap = devaIndependentVowelsToRoman ++ devaToRomanGeneral ++ devaConsonantsToRoman
    output = replaceKeysLongestFirst(output, replacementMap)
    output
  }

  def restoreEscapeSequences(str_in: String): String = {
    var output = str_in
    val escapePattern = """\\(.्?)""".r
    output = escapePattern.replaceAllIn(output, _ match { case escapePattern(matched) => """\\""" + fromDevanagari(matched) })
    output
  }
  def test_restoreEscapeSequences(): Unit = {
    val str1 = """हरिः ॐ १ 1ad\न् \त्"""
    log.info(restoreEscapeSequences(str1))
  }

  // ASSUMPTION: Escape characters appropriately in romanStart and romanEnd.
  def restoreRomanBetweenStrings(str_in: String, romanStart: String, romanEnd: String): String = {
    var output = str_in
    val escapePattern = (romanStart + """(.+?)""" + romanEnd).r
    output = escapePattern.replaceAllIn(output, _ match { case escapePattern(matched) => romanStart + fromDevanagari(matched) + romanEnd})
    output
  }
  def test_restoreRomanBetweenStrings(): Unit = {
    val str1 = """हरिः ॐ १ {#Pअगे#} 1ad {#आन्द्#} \न् \त्"""
    log.info(restoreRomanBetweenStrings(str1, "\\{#", "#\\}"))
  }

  def test_toDevanagari(str_in : String): Unit = {
    log.info(toDevanagari(str_in))
  }

  def test_fromDevanagari(str_in : String = "असय औषधिः ग्रन्थः! ॡकारो।ऽस्ति। नास्ति लेशोऽपि संशयः। कीलकम्? कूपिः?  कष्ठं भोः। शङ्कर! ज्ञानम्।  सञ्जीवय। १२३४५.. ॐ तत्।"): Unit = {
    log.info("Input: " + str_in)
    log.info("Output: " + fromDevanagari(str_in))
  }
}
