package sanskritnlp.transliteration.roman

// Partial implementation of a variant of https://sites.google.com/site/sanskritcode/optitrans
object optitrans extends RomanScript {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "A" -> "आ",  "aa" -> "आ",
    "i" -> "इ", "I" -> "ई",  "ii" -> "ई",
    "u" -> "उ", "U" -> "ऊ", "uu" -> "ऊ",
    "R" -> "ऋ", "RR" -> "ॠ",
    "LLi" -> "ऌ", "LLI" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ",
    "au" -> "औ"
  )

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "NJ" -> "ञ्",
    "NG" -> "ङ्",
    "m" -> "म्",
    "N" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "J" -> "झ्", "bh" -> "भ्", "B" -> "भ्",
    "gh" -> "घ्", "G" -> "घ्", "Dh" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "D" -> "ड्", "d" -> "द्",
    "kh" -> "ख्", "K" -> "ख्",
    "ph" -> "फ्", "P" -> "फ्", "Ch" -> "छ्", "C" -> "छ्", "Th" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "ch" -> "च्", "T" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "sh" -> "श्", "S" -> "ष्", "Sh" -> "ष्", "s" -> "स्",
    "L" -> "ळ्", "f" -> "फ़्", "z" -> "ज़्",
    "x" -> "क्ष्",
    "nk" -> "ङ्क्", "nK" -> "ङ्ख्",  "nkh" -> "ङ्ख्","nx" -> "ङ्क्ष्",
    "ng" -> "ङ्ग्", "nG" -> "ङ्ख्",  "ngh" -> "ङ्ख्",
    "nc" -> "ञ्च्", "nC" -> "ञ्छ्", "nch" -> "ञ्च्", "nCh" -> "ञ्छ्",
    "nj" -> "ञ्ज्", "nJ" -> "ञ्झ्", "njh" -> "ञ्झ्", "JN" -> "ज्ञ्"
  )
  override val romanToDevaConsonantsNoVirama: Map[String, String] = romanToDevaConsonants.view.mapValues(_.replaceAll("(.+)्$", "$1")).toMap
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः", ".N" -> "ँ",
    "." -> "।", ".." -> "॥", ".a" -> "ऽ",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "OM" -> "ॐ")

  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.view.filterKeys(key => !(List("A", "I", "U") contains key)).map(_.swap).toMap ++ Map(
    "ऎ" -> "E", "ऒ" -> "O"
  )
  private val devaConsonantsToRomanWithoutNutka = romanToDevaConsonants.view.filterKeys(key => !(List("K", "G", "c", "C", "J", "S", "nK", "nG", "nc", "nC", "nJ") contains key)).map(_.swap).toMap ++ Map("ड़्" -> ".D", "ड़्" -> ".D" , "ढ़्" -> ".Dh", "य़्" -> "Y", "क़्" -> "k", "ख़्" -> "kh", "ग़्" -> "g", "ऴ्" -> ".L", "ऱ्"-> ".Rh")
  override val devaConsonantsToRoman: Map[String, String] = devaConsonantsToRomanWithoutNutka ++ devaConsonantsToRomanWithoutNutka.map({
    case (deva: String, roman: String) => {
      (deva.head + "़्", "." + roman)
    }
  })

  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)


  def test_toDevanagari(): Unit = {
    val text = "asaya auSadhiH auShadhiH granthaH! LLIkAro.asti. nAsti lesho.api saMshayaH. kaSThaM bhoH. shankara! sanjIvaya. 12345"
    println("OPTITRANS Tests.")
    test_toDevanagari(text)
  }

}
