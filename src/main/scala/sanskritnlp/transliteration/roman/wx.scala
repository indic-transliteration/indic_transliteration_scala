package sanskritnlp.transliteration.roman

object wx extends RomanScript {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "A" -> "आ",  "i" -> "इ", "I" -> "ई",
    "u" -> "उ", "U" -> "ऊ",
    "q" -> "ऋ", "Q" -> "ॠ",
    "L" -> "ऌ", "ḹ" -> "ॡ",
    "e" -> "ए",
    "E" -> "ऐ",
    "o" -> "ओ", "O" -> "औ")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "F" -> "ञ्",
    "f" -> "ङ्",
    "m" -> "म्",
    "R" -> "ण्",
    "n" -> "न्",
    "J" -> "झ्", "B" -> "भ्",
    "G" -> "घ्", "D" -> "ढ्", "X" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "d" -> "ड्", "x" -> "द्",
    "K" -> "ख्",
    "P" -> "फ्", "C" -> "छ्", "T" -> "ठ्",
    "W" -> "थ्", "c" -> "च्", "t" -> "ट्", "w" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "S" -> "श्", "R" -> "ष्", "s" -> "स्",
    "ḻ" -> "ळ्")
  override val romanToDevaConsonantsNoVirama: Map[String, String] = romanToDevaConsonants.mapValues(_.replaceAll("्", ""))
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः", "z" -> "ँ",
    "Z" -> "ऽ", ".." -> "॥", "." -> "।",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "oM" -> "ॐ")

  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
  override val devaConsonantsToRoman: Map[String, String] = romanToDevaConsonants.map(_.swap)
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)

  def test_toDevanagari(): Unit = {
    val text = "aH, puM, awwi axXA atawi"
    println("WX Tests.")
    test_toDevanagari(text)
  }

}
