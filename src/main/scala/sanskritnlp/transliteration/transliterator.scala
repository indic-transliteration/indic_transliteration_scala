package sanskritnlp.transliteration

import org.slf4j.LoggerFactory

/**
  * General transliteration utilities.
  */
object transliterator {
  val log = LoggerFactory.getLogger(getClass.getName)
  val scriptDevanAgarI = "dev"
  val scriptUnknown = null
  val codeToSchemeMap = Map(
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

  // Transliterate among roman schemes + devanAgarI via devanAgarI.
  def transliterate(in_str: String, sourceScheme: String, destScheme: String): String = {
    // println("input string: " + in_str)
    var schemeOption = codeToSchemeMap.get(sourceScheme)
    var devanAgarIout = in_str
    if (schemeOption.isDefined) {
      val return_opt = schemeOption.get.toDevanagari(in_str)
      // println("return_opt: " + return_opt)
      devanAgarIout = return_opt
    } else {
      throw new IllegalArgumentException(s"Unrecognized scheme $sourceScheme")
    }
    schemeOption = codeToSchemeMap.get(destScheme)
    if (schemeOption.isDefined) {
      return schemeOption.get.fromDevanagari(devanAgarIout)
    } else {
      if (destScheme == scriptDevanAgarI) {
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
    * Sources of orthtographically divergent forms:
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
          // Remove digits, abbreviation sign, svara-s
          .replaceAll("[०-९।॥॰ ॒॑]", "")
          // Collapse semi-vowel-anunAsika-s संलग्नम् सल्ँलग्नम् into m
          .replaceAll("[[ल्य्व्]ँ]", "म्")
          // Collapse all panchama-s into m
          .replaceAll("[ङञणन]", "म")
          // Collapse anusvAra into m
          .replaceAll("ँ|ं", "म्")
          .replaceAll("ॐ", "ओम्")
          .replaceAll("[ळऴ]", "ल")
          // Deal with optional forms where consonants are duplicated - like dharmma
          // Details in https://docs.google.com/spreadsheets/d/1GP8Ps_hmgCGLZPWKIVBCfQB9ZmPQOaCwTrH9OybaWaQ/edit#gid=21
          .replaceAll("([क-हक़-य़]्)\\1+", "$1")
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
        log warn (s"got script $unknownScript for text [$text]")
        return text.replaceAll("\\s", "")
      }
    }
  }

  def main(args: Array[String]): Unit = {

  }
}
