package sanskritnlp.transliteration.roman

object harvardKyoto extends RomanScript {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "A" -> "आ",  "i" -> "इ", "I" -> "ई",
    "u" -> "उ", "U" -> "ऊ",
    "R" -> "ऋ", "RR" -> "ॠ",
    ".r" -> "ऋ", // In TITUS
    "lR" -> "ऌ", "lRR" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "~N" -> "ञ्", // In TITUS. Unusual.
    ".N" -> "ङ्", // In TITUS.  Unusual.
    "J" -> "ञ्",
    "G" -> "ङ्",
    "m" -> "म्",
    "N" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "bh" -> "भ्",
    "gh" -> "घ्", "Dh" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "D" -> "ड्", "d" -> "द्",
    "kh" -> "ख्",
    "ph" -> "फ्", "ch" -> "छ्", "Th" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "T" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "z" -> "श्", "S" -> "ष्", "s" -> "स्",
    ".l" -> "ळ्", // In TITUS
    "L" -> "ळ्")
  override val romanToDevaConsonantsNoVirama: Map[String, String] = romanToDevaConsonants.view.mapValues(_.replaceAll("(.+?)्$", "$1")).toMap
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः",
    "'" -> "ऽ", "." -> "।", "|" -> "।", "||" -> "॥",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "OM" -> "ॐ")

  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
  override val devaConsonantsToRoman: Map[String, String] = romanToDevaConsonants.view.filterKeys(!List(".N", "~N").contains(_)).map(_.swap).toMap
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)

  def test_toDevanagari(): Unit = {
    val hkText = "asaya auSadhiH granthaH! lRRkAro'sti. nAsti lezo'pi saMzayaH. kaSThaM bhoH. abala 12345"
    println("HK Tests.")
    test_toDevanagari(hkText)
  }

}
