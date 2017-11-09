package sanskritnlp.transliteration


import org.scalatest.FlatSpec

import scala.io.Source
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import org.slf4j.LoggerFactory

class GetApproxDeduplicatingKeySpec extends FlatSpec {
  val log = LoggerFactory.getLogger(this.getClass)

  "getApproxDeduplicatingKey" should "provide the same key for certain cases" in {
    val source = Source.fromResource("approxDeduplicationTests.json")
    log.debug(source.mkString)
    assert(transliterator.getApproxDeduplicatingKey(text = "धर्म्म") == "धर्म")
  }

}