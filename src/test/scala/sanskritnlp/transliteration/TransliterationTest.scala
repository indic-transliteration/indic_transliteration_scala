package sanskritnlp.transliteration


import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.FlatSpec
import org.slf4j.LoggerFactory

import scala.io.Source

case class Test(
                 dev: Option[String],
                 iast: Option[String],
                 iastDcs: Option[String],
                 hk: Option[String],
                 optitrans: Option[String],
                 slp: Option[String],
                 wx: Option[String]
               )
case class TransliterationTests(
                                 canonical_source: Option[String],
                                 tests: List[Test]
                               )

class TransliterationTest extends FlatSpec {
  val log = LoggerFactory.getLogger(this.getClass)

  "transliterator" should "work" in {
    val source = Source.fromResource("transliterationTests.json")
    implicit val formats = DefaultFormats
    val testJson = Serialization.read[TransliterationTests](source.mkString)
    testJson.tests.foreach(test => {
      log.debug(test.toString)
//      if (test.iast.isDefined) {
//        assert(transliterator.transliterate(in_str = test.dev.get, sourceScheme = transliterator.scriptDevanAgarI, destScheme = "iast") == test.iast.get)
//        assert(transliterator.transliterate(in_str = test.iast.get, sourceScheme =  "iast", destScheme = "dev") == test.dev.get)
//      }
//      if (test.hk.isDefined) {
//        assert(transliterator.transliterate(in_str = test.dev.get, sourceScheme = transliterator.scriptDevanAgarI, destScheme = "hk") == test.hk.get)
//        assert(transliterator.transliterate(in_str = test.hk.get, sourceScheme = "hk", destScheme = "dev") == test.dev.get)
//      }
//      if (test.optitrans.isDefined) {
//        assert(transliterator.transliterate(in_str = test.dev.get, sourceScheme = transliterator.scriptDevanAgarI, destScheme = "optitrans") == test.optitrans.get)
//        assert(transliterator.transliterate(in_str = test.optitrans.get, sourceScheme = "optitrans", destScheme = "dev") == test.dev.get)
//      }
//      if (test.slp.isDefined) {
//        assert(transliterator.transliterate(in_str = test.dev.get, sourceScheme = transliterator.scriptDevanAgarI, destScheme = "slp") == test.slp.get)
//        assert(transliterator.transliterate(in_str = test.slp.get, sourceScheme = "slp", destScheme = "dev") == test.dev.get)
//      }
//      if (test.wx.isDefined) {
//        assert(transliterator.transliterate(in_str = test.dev.get, sourceScheme = transliterator.scriptDevanAgarI, destScheme = "wx") == test.wx.get)
//        assert(transliterator.transliterate(in_str = test.wx.get, sourceScheme = "wx", destScheme = "dev") == test.wx.get)
//      }
//      if (test.iastDcs.isDefined) {
//        assert(transliterator.transliterate(in_str = test.dev.get, sourceScheme = transliterator.scriptDevanAgarI, destScheme = "iastDcs") == test.iastDcs.get)
//        assert(transliterator.transliterate(in_str = test.iastDcs.get, sourceScheme = "iastDcs", destScheme = "dev") == test.dev.get)
//      }
    })
  }
}