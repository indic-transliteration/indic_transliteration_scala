package sanskritnlp.transliteration.roman

object slp extends RomanScript {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "A" -> "आ",  "i" -> "इ", "I" -> "ई",
    "u" -> "उ", "U" -> "ऊ",
    "f" -> "ऋ", "F" -> "ॠ",
    "x" -> "ऌ", "X" -> "ॡ",
    "e" -> "ए",
    "E" -> "ऐ",
    "o" -> "ओ", "O" -> "औ")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "Y" -> "ञ्",
    "N" -> "ङ्",
    "m" -> "म्",
    "R" -> "ण्",
    "n" -> "न्",
    "J" -> "झ्", "B" -> "भ्",
    "G" -> "घ्", "Q" -> "ढ्", "D" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "q" -> "ड्", "d" -> "द्",
    "K" -> "ख्",
    "P" -> "फ्", "C" -> "छ्", "W" -> "ठ्",
    "T" -> "थ्", "c" -> "च्", "w" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "S" -> "श्", "z" -> "ष्", "s" -> "स्",
    "L" -> "ळ्")
  override val romanToDevaConsonantsNoVirama: Map[String, String] = romanToDevaConsonants.view.mapValues(_.replaceAll("्", "")).toMap
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः",
    "'" -> "ऽ", ".." -> "॥", "." -> "।",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "oM" -> "ॐ")

  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
  override val devaConsonantsToRoman: Map[String, String] = romanToDevaConsonants.map(_.swap)
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)

  def test_toDevanagari(): Unit = {
    val text = "aH, puM, (atati sarvvaM vyApnoti iti ataterqaH) vizRuH . iti medinI .  “akAro vizRuruddizwa ukArastu maheSvaraH . makAra ucyate brahmA praRavena trayo matAH” .. iti durgAdAsaDftavacanaM . (klI . brahma . yaTA, -- a i u e o om kalASca mUlaM brahma iti kIrttitam, iti agnipurARam .)"
    println("SLP Tests.")
    test_toDevanagari(text)
  }

}
