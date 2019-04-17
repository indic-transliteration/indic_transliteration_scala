package sanskritnlp.transliteration


import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.{FlatSpec, FunSuite}
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

case class TransliterationTests(canonical_source: Option[String],
                                devanaagarii_round_trip: List[Map[String, String]],
                                to_devanaagarii: List[Map[String, String]],
                                from_devanaagarii: List[Map[String, String]]
                               )

class TransliterationTest extends FunSuite {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)
  private val source = Source.fromResource("transliterationTests.json")
  private implicit val formats = DefaultFormats
  private val testJson = Serialization.read[TransliterationTests](source.mkString)
  private val nonSchemeKeys = Seq(transliterator.scriptDevanAgarI, "description", "TODO", "comments")

  testJson.devanaagarii_round_trip.foreach(test => {
    log.info(test.toString)
    test.filterKeys(!nonSchemeKeys.contains(_)).foreach {
      case (scheme: String, value: String) => {
        log.info(s"$scheme : $value")
        assert(transliterator.transliterate(
          in_str = test(transliterator.scriptDevanAgarI), 
          sourceScheme = transliterator.scriptDevanAgarI, 
          destScheme = scheme) == 
          test(scheme))
        
        assert(transliterator.transliterate(
          in_str = test(scheme), 
          sourceScheme = scheme, 
          destScheme = transliterator.scriptDevanAgarI) == 
          test(transliterator.scriptDevanAgarI))
      }
    }
  }
  )

  testJson.to_devanaagarii.foreach(test => {
    log.info(test.toString)
    test.filterKeys(!nonSchemeKeys.contains(_)).foreach {
      case (scheme: String, value: String) => {
        log.info(s"$scheme : $value")
        assert(transliterator.transliterate(in_str = test(scheme), sourceScheme = scheme, destScheme = transliterator.scriptDevanAgarI) == test(transliterator.scriptDevanAgarI))
      }
    }
  })


  testJson.from_devanaagarii.foreach(test => {
    log.info(test.toString)
    test.filterKeys(!nonSchemeKeys.contains(_)).foreach {
      case (scheme: String, value: String) => {
        log.info(s"$scheme : $value")
        assert(transliterator.transliterate(in_str = test(transliterator.scriptDevanAgarI), sourceScheme = transliterator.scriptDevanAgarI, destScheme = scheme) == test(scheme))
      }
    }
  })

}