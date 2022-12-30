package sanskritnlp.transliteration.roman

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
  override val devaIndependentVowelsToRoman: Map[String, String] = romanToDevaIndependentVowels.map(_.swap)
}
