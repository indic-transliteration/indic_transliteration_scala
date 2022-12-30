package sanskritnlp.transliteration.roman

object unrsgn extends IastBase {
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap) ++ Map("॥" -> "..")
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "ā" -> "आ", "i" -> "इ", "ī" -> "ई",
    "u" -> "उ", "ū" -> "ऊ",
    "ṛ" -> "ऋ", "ṝ" -> "ॠ", "l̤" -> "ऌ", "l̤̄" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")
  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro`sti. nāsti les4o`pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ. 12345" +
      "Aṃkuśeśvaram. iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}
