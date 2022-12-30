package sanskritnlp.transliteration.roman

object iastDcs extends IastBase {
  override val romanToDevaContextFreeReplacements = Map(
    "ṃ" -> "ं",  "ḥ" -> "ः", "//" -> "॥", "/" -> "।",
    "'" -> "ऽ", "." -> "॰",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "aum" -> "ॐ")
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)
  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)

  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro'sti. nāsti les4o'pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ/ 12345 10.2" +
      "Aṃkuśeśvaram// iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}
