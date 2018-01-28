package sanskritnlp.transliteration


import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.FlatSpec
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

case class TransliterationTests(
                                 canonical_source: Option[String],
                                 devanaagarii_round_trip: List[Map[String, String]]
                               )

class TransliterationTest extends FlatSpec {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  "transliterator" should "work" in {
    val source = Source.fromResource("transliterationTests.json")
    implicit val formats = DefaultFormats
    val testJson = Serialization.read[TransliterationTests](source.mkString)
    testJson.devanaagarii_round_trip.foreach(test => {
      log.info(test.toString)
      test.filterKeys(_ != transliterator.scriptDevanAgarI).foreach {
        case (scheme: String, value: String) => {
          log.info(s"$scheme : $value")
          assert(transliterator.transliterate(in_str = test(transliterator.scriptDevanAgarI), sourceScheme = transliterator.scriptDevanAgarI, destScheme = scheme) == test(scheme))
          assert(transliterator.transliterate(in_str = test(scheme), sourceScheme = scheme, destScheme = transliterator.scriptDevanAgarI) == test(transliterator.scriptDevanAgarI))
        }
      }
    })
  }
}