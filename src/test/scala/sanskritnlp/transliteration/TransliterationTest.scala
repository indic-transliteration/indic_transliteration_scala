package sanskritnlp.transliteration


import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.FlatSpec
import org.slf4j.LoggerFactory

import scala.io.Source

case class TransliterationTests(
                                 canonical_source: Option[String],
                                 tests: List[Map[String, String]]
                               )

class TransliterationTest extends FlatSpec {
  val log = LoggerFactory.getLogger(this.getClass)

  "transliterator" should "work" in {
    val source = Source.fromResource("transliterationTests.json")
    implicit val formats = DefaultFormats
    val testJson = Serialization.read[TransliterationTests](source.mkString)
    testJson.tests.foreach(test => {
      log.debug(test.toString)
      test.filterKeys(_ != transliterator.scriptDevanAgarI).foreach {
        case (scheme: String, value: String) => {
          log.info(scheme)
          assert(transliterator.transliterate(in_str = test(transliterator.scriptDevanAgarI), sourceScheme = transliterator.scriptDevanAgarI, destScheme = scheme) == test(scheme))
          assert(transliterator.transliterate(in_str = test(scheme), sourceScheme = scheme, destScheme = transliterator.scriptDevanAgarI) == test(transliterator.scriptDevanAgarI))
        }
      }
    })
  }
}