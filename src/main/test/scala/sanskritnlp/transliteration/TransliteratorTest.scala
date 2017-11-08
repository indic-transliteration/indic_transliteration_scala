package sanskritnlp.transliteration


import org.scalatest.FlatSpec

class GetApproxDeduplicatingKeySpec extends FlatSpec {

  "getApproxDeduplicatingKey" should "provide the right key" in {
    assert(transliterator.getApproxDeduplicatingKey(text = "धर्म्म") == "धर्म")
  }

}