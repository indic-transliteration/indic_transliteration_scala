package sanskritnlp.transliteration

import sun.util.resources.LocaleNames

import scala.collection.SetLike

/**
  * General transliteration utilities.
  * Created by vvasuki on 2/21/16.
  */
object transliterator {
  val scriptDevanAgarI = "dev"
  val scriptUnknown = null
  val codeToSchemeMap = Map(
    "hk" -> harvardKyoto,
    "iast" -> iast,
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
    var schemeOpt = codeToSchemeMap.get(sourceScheme)
    var devanAgarIout = in_str
    if (schemeOpt.isDefined) {
      val return_opt = schemeOpt.get.toDevanagari(in_str)
      // println("return_opt: " + return_opt)
      devanAgarIout = return_opt
    }
    schemeOpt = codeToSchemeMap.get(destScheme)
    if (schemeOpt.isDefined) {
      return schemeOpt.get.fromDevanagari(devanAgarIout)
    } else {
      if (destScheme == scriptDevanAgarI) {
        return devanAgarIout
      } else {
        throw new IllegalArgumentException("Could not transliterate " + in_str)
      }
    }
  }
}
