package sanskritnlp.transliteration

import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.FlatSpec
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

case class ScriptDetectionTests(canonical_source: Option[String],
                                 tests: Map[String, String],
                                 unmatchables: Seq[String]
                               )

class ScriptDetectionTest extends FlatSpec {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)
  private val source = Source.fromResource("scriptDetectionTests.json")
  private implicit val formats: DefaultFormats.type = DefaultFormats
  private val testJson = Serialization.read[ScriptDetectionTests](source.mkString)
  private val nonSchemeKeys = Seq(transliterator.scriptDevanAgarI, "description", "TODO", "comments")

  "Shared script detection tests " should "match the correct script" in {
    testJson.tests.foreach({
        case (scheme: String, value: String) => {
          log.info(s"$scheme : $value")
          val script = transliterator.getScriptHandler(value).map(_.getClass.getSimpleName.replace("$", ""))
          assert(script.getOrElse("") == scheme)
        }
    })
  }

  "Unmatchables " should "not be matched" in {
    testJson.unmatchables.foreach({
      case (value: String) => {
        log.info(s"$value")
        val script = transliterator.getScriptHandler(value).map(_.getClass.getSimpleName.replace("$", ""))
        assert(script.isEmpty)
      }
    })
  }
}