package sanskritnlp.transliteration


import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.FlatSpec
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

case class ApproxDeduplicationTests(
                           canonical_source: Option[String],
                           duplicates: Seq[Seq[String]],
                           non_duplicates: Seq[Seq[String]]
                         )

class GetApproxDeduplicatingKeySpec extends FlatSpec {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  "getApproxDeduplicatingKey" should "provide the same key for certain cases" in {
    val source = Source.fromResource("approxDeduplicationTests.json")
    implicit val formats: DefaultFormats.type = DefaultFormats
    val approxDeduplicationTests = Serialization.read[ApproxDeduplicationTests](source.mkString)
    log.debug(approxDeduplicationTests.toString)

    log.info(s"Checking ${approxDeduplicationTests.duplicates.size} duplicate sets.")
    approxDeduplicationTests.duplicates.foreach(duplicatesList => {
//      log.debug(duplicatesList.map(x => transliterator.getApproxDeduplicatingKey(text = x)).toString)
      val keys = duplicatesList.map(x => transliterator.getApproxDeduplicatingKey(text = x)).toSet
      log.debug(s"Checking ${duplicatesList.mkString(",")}.")
      log.debug(s"Got keys ${keys.mkString(",")}.")
      assert(keys.size == 1)
    })

    log.info(s"Checking ${approxDeduplicationTests.non_duplicates.size} non-duplicate sets.")
    approxDeduplicationTests.non_duplicates.foreach(nonDuplicatesList => {
      val keys = nonDuplicatesList.map(x => transliterator.getApproxDeduplicatingKey(text = x)).toSet
      log.debug(s"Checking ${nonDuplicatesList.mkString(",")}.")
      assert(keys.size != 1)
    })

  }

}