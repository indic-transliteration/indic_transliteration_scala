package sanskritnlp.transliteration

import org.slf4j.{Logger, LoggerFactory}

/**
  * General transliteration utilities.
  */
object transliterator {
  private val log: Logger = LoggerFactory.getLogger(getClass.getName)
  val scriptDevanAgarI: String = "dev"
  val scriptUnknown: Null = null
  val codeToSchemeMap: Map[String, IndicScript] = Map(
    "hk" -> harvardKyoto,
    "iast" -> iast,
    "iastDcs" -> iastDcs,
    "as" -> as,
    "slp" -> slp,
    "wx" -> wx,
    "optitrans" -> optitrans,
    "kn" -> kannaDa
  )

  // Assumes that words are space separable.
  def transliterateWordsIfIndic(in_str: String, wordSet: Set[String], sourceScheme: String, destScheme: String): String = {
    val words = in_str.split("\\s+")
    words.map(word => {
      if (wordSet.contains(word)) {
        transliterate(word, sourceScheme, destScheme)
      } else if(word.endsWith("s")) {
        if (wordSet.contains(word.dropRight(1))) {
          transliterate(word, sourceScheme, destScheme) + "-s"
        }
      } else {
        word
      }
    }).mkString(" ")

  }

  def getNonAnusvaaraVariant(in_str: String): String = {
    in_str
      .replaceAll("ं(\\s*[क-ङ])", "ङ्$1")
      .replaceAll("ं(\\s*[च-ञ])", "ञ्$1")
      .replaceAll("ं(\\s*[त-न])", "न्$1")
      .replaceAll("ं(\\s*[ट-ण])", "ण्$1")
      .replaceAll("ं(\\s*[प-म])", "म्$1")
      .replaceAll("ं$", "म्")
  }

  def getScriptHandler(in_str: String): Option[NativeIndicScript] = {
    // The more distinct and constrained script should appear fist.
    val indicMaps = Seq(gurmukhi, oriya, kannaDa, telugu, malayalam, gujarati, devanagarii)
    indicMaps.find(script => script.isEncoding(in_str))
  }

  def getDevanagariiFromOtherIndicString(in_str: String): Option[String] = {
    val scriptHandler = getScriptHandler(in_str = in_str)
    scriptHandler.map(_.toDevanagari(in_str))
  }

  // Transliterate among roman schemes + devanAgarI via devanAgarI.
  def transliterate(in_str: String, sourceScheme: String, destScheme: String): String = {
    // println("input string: " + in_str)
    var schemeOption = codeToSchemeMap.get(sourceScheme)
    var devanAgarIout = in_str
    if (sourceScheme != scriptDevanAgarI) {
      if (schemeOption.isDefined) {
        val return_opt = schemeOption.get.toDevanagari(in_str)
        // println("return_opt: " + return_opt)
        devanAgarIout = return_opt
      } else {
        throw new IllegalArgumentException(s"Unrecognized scheme $sourceScheme")
      }
    }

    schemeOption = codeToSchemeMap.get(destScheme)
    if (schemeOption.isDefined) {
      //noinspection RemoveRedundantReturn
      return schemeOption.get.fromDevanagari(devanAgarIout)
    } else {
      if (destScheme == scriptDevanAgarI) {
        //noinspection RemoveRedundantReturn
        return devanAgarIout
      } else {
        throw new IllegalArgumentException(s"Unrecognized scheme $destScheme")
      }
    }
  }

  /**
    * Given some devanAgarI sanskrit text, this function produces a "key" so that
    * 1] The key should be the same for different observed orthographical forms of the same text. For example:
    *   - "dharmma" vs "dharma"
    *   - "rAmaM gacChati" vs "rAma~N gacChati" vs "rAma~N gacChati"
    *   - "kurvan eva" vs "kurvanneva"
    * 2] The key should be different for different for different texts.
    *   - "stamba" vs "stambha"
    *
    * This function attempts to succeed at [1] and [2] *almost* all the time.
    * Longer the text, probability of failing at [2] decreases, while probability of failing at [1] increases (albeit very slightly).
    *
    * Sources of orthographically divergent forms:
    *   - Phonetically sensible grammar rules
    *   - Neglect of sandhi while writing
    *   - Punctuation, spaces, avagraha-s.
    *   - Regional-language-influenced mistakes (La instead of la.)
    *
    * Some example applications of this function:
    *    - Create a database of quotes or words with minimal duplication.
    *    - Search a database of quotes or words while being robust to optional forms.
    */
  def getApproxDeduplicatingKey(text: String, encodingScheme: String = scriptDevanAgarI): String = {
    encodingScheme match {
      case `scriptDevanAgarI` => {
        var key = text.replaceAll("\\P{IsDevanagari}", "")
          // Remove spaces
          .replaceAll("\\s", "")
          // Remove punctuations
          .replaceAll("\\p{P}", "")
          // Remove digits, abbreviation sign, svara-s, avagraha
          .replaceAll("[०-९।॥॰ऽ]|[॑-॔]", "")
          // Collapse semi-vowel-anunAsika-s संलग्नम् सल्ँलग्नम् into m
          .replaceAll("[यरल]्ँ", "म्")
          // Collapse all panchama-s into m
          .replaceAll("[ङञणन]", "म")
          // Collapse anusvAra into m
          .replaceAll("ँ|ं", "म्")
          .replaceAll("ॐ", "ओम्")
          .replaceAll("[ळऴ]", "ल")
          // Deal with optional forms where consonants are duplicated - like dharmma
          // Details in https://docs.google.com/spreadsheets/d/1GP8Ps_hmgCGLZPWKIVBCfQB9ZmPQOaCwTrH9OybaWaQ/edit#gid=21
          .replaceAll("([क-हक़-य़])्\\1+", "$1")
          key = key
          .replaceAll("[कग]्ख्", "ख्")
          .replaceAll("[कग]्घ्", "घ्")
          .replaceAll("च्छ्", "छ्")
          .replaceAll("ज्झ्", "झ्")
          .replaceAll("त्थ्", "थ्")
          .replaceAll("द्ध्", "ध्")
          .replaceAll("ड्ढ्", "ढ्")
          .replaceAll("प्फ्", "फ्")
          .replaceAll("ब्भ्", "भ्")
        return key
      }
      case unknownScript => {
        log warn s"got script $unknownScript for text [$text]"
        return text.replaceAll("\\s", "")
      }
    }
  }

  def main(args: Array[String]): Unit = {

  }
}
