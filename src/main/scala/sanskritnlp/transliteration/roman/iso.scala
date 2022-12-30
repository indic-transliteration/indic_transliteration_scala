package sanskritnlp.transliteration.roman

object iso extends IastBase {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "ā" -> "आ", "i" -> "इ", "ī" -> "ई",
    "u" -> "उ", "ū" -> "ऊ",
    "r̥" -> "ऋ", "r̥̄" -> "ॠ", "l̥" -> "ऌ", "l̥̄" -> "ॡ",
    "e" -> "ऎ", "ē" -> "ए",
    "ai" -> "ऐ", "o" -> "ऒ",
    "ō" -> "ओ", "au" -> "औ")
  override val romanToDevaContextFreeReplacements = Map(
    "ṁ" -> "ं", "m̐" ->  "◌ँ" , "ḥ" -> "ः",
    "'" -> "ऽ", "." -> "।", ".." -> "॥", "||" -> "॥",
    "0" -> "०", "1" -> "१", "2" -> "२",
    "3" -> "३", "4" -> "४", "5" -> "५",
    "6" -> "६", "7" -> "७", "8" -> "८", "9" -> "९", "ōṁ" -> "ॐ")
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)

  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro'sti. nāsti les4o'pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ/ 12345 10.2" +
      "Aṃkuśeśvaram// iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}
