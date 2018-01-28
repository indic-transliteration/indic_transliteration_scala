package sanskritnlp.transliteration

import org.slf4j.{Logger, LoggerFactory}

trait IastBase extends RomanScript {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  override val caseNeutral = true
  override val distinctCharacters = List("ṇ", "ṃ", "ś", "ñ", "u1", "ṣ", "ḥ", "ṭ", "ī", "ṝ", "ḍ", "ḷ", "ḹ", "ṛ", "ā", "ṅ")
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "ā" -> "आ",  "i" -> "इ", "ī" -> "ई",
    "u" -> "उ", "ū" -> "ऊ",
    "ṛ" -> "ऋ", "ṝ" -> "ॠ", "ḷ" -> "ऌ", "ḹ" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "ñ" -> "ञ्",
    "ṅ" -> "ङ्",
    "m" -> "म्",
    "ṇ" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "bh" -> "भ्",
    "gh" -> "घ्", "ḍh" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "ḍ" -> "ड्", "d" -> "द्",
    "kh" -> "ख्",
    "ph" -> "फ्", "ch" -> "छ्", "ṭh" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "ṭ" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "ś" -> "श्", "ṣ" -> "ष्", "s" -> "स्", "ḻ" -> "ळ्")
  override val romanToDevaConsonantsNoVirama: Map[String, String] = romanToDevaConsonants.mapValues(_.replaceAll("(.+)्$", "$1"))
  override val romanToDevaContextFreeReplacements = Map(
    "ṃ" -> "ं",  "ḥ" -> "ः",
    "`" -> "ऽ", "." -> "।", ".." -> "॥",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "aum" -> "ॐ")

  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
  override val devaConsonantsToRoman: Map[String, String] = romanToDevaConsonants.map(_.swap)

  def test_isEncoding(): Unit = {
    log info isEncoding("Aṃkuśeśvaram").toString
    log.info(isEncoding("Aṃkuśeśvaram").toString)
  }
}

object iast extends IastBase {
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)

  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro`sti. nāsti les4o`pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ. 12345" +
      "Aṃkuśeśvaram. iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}

object iastDcs extends IastBase {
  override val romanToDevaContextFreeReplacements = Map(
    "ṃ" -> "ं",  "ḥ" -> "ः", "//" -> "॥", "/" -> "।",
    "'" -> "ऽ", "." -> "॰",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "aum" -> "ॐ")
  override val devaToRomanGeneral: Map[String, String] = romanToDevaContextFreeReplacements.map(_.swap)

  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro'sti. nāsti les4o'pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ/ 12345 10.2" +
      "Aṃkuśeśvaram// iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}

/*
If the below is uncommented, and the kolkata line in transliterator is also uncommented, I get:
[info] sanskritnlp.transliteration.GetApproxDeduplicatingKeySpec *** ABORTED ***
[info]   java.lang.ExceptionInInitializerError:
[info]   at sanskritnlp.transliteration.transliterator$.<init>(transliterator.scala:20)
[info]   at sanskritnlp.transliteration.transliterator$.<clinit>(transliterator.scala)
[info]   at sanskritnlp.transliteration.GetApproxDeduplicatingKeySpec.$anonfun$new$3(DeduplicationTest.scala:29)
...
[info]   Cause: java.lang.NullPointerException:
[info]   at sanskritnlp.transliteration.IastBase.$init$(iast.scala:42)
[info]   at sanskritnlp.transliteration.kolkata$.<init>(iast.scala:81)
 */
object kolkata extends IastBase {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "ā" -> "आ",  "i" -> "इ", "ī" -> "ई",
    "u" -> "उ", "ū" -> "ऊ",
    "ṛ" -> "ऋ", "ṝ" -> "ॠ", "ḷ" -> "ऌ", "ḹ" -> "ॡ",
    "ē" -> "ए",
    "ai" -> "ऐ",
    "ō" -> "ओ", "au" -> "औ")
  override val devaIndependentVowelsToRoman = romanToDevaIndependentVowels.map(_.swap)
}

object iastTest {
  def main(args: Array[String]): Unit = {
    iast.test_toDevanagari()
    iast.test_fromDevanagari()
    iast.test_isEncoding()
  }
}

object iastDcsTest {
  def main(args: Array[String]): Unit = {
    iastDcs.test_toDevanagari()
    iastDcs.test_fromDevanagari()
    iastDcs.test_isEncoding()
  }
}

