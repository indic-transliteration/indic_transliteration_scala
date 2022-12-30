package sanskritnlp.transliteration.roman

object iast extends IastBase {
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap) ++ Map("॥" -> "..")
  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)

  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro`sti. nāsti les4o`pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ. 12345" +
      "Aṃkuśeśvaram. iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}
