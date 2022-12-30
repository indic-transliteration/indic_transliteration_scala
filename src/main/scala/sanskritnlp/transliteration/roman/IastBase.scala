package sanskritnlp.transliteration.roman

import org.slf4j.{Logger, LoggerFactory}
import sanskritnlp.transliteration.roman.optitrans.romanToDevaContextFreeReplacements

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
  override val romanToDevaConsonantsNoVirama: Map[String, String] = romanToDevaConsonants.view.mapValues(_.replaceAll("(.+)्$", "$1")).toMap
  override val romanToDevaContextFreeReplacements = Map(
    "ṃ" -> "ं",  "m̐" ->  "◌ँ" , "ḥ" -> "ः",
    "'" -> "ऽ", "." -> "।", ".." -> "॥", "||" -> "॥",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "oṃ" -> "ॐ")

  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
  override val devaConsonantsToRoman: Map[String, String] = romanToDevaConsonants.map(_.swap)

  def test_isEncoding(): Unit = {
    log info isEncoding("Aṃkuśeśvaram").toString
    log.info(isEncoding("Aṃkuśeśvaram").toString)
  }
}
