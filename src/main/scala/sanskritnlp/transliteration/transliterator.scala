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

  def main(args: Array[String]): Unit = {

  }
}
