package sanskritnlp.transliteration

import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.funsuite.AnyFunSuite
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source


case class VariantPair(
                        form_with_anusvaara: String,
                        form_without_anusvaara: String
                      )

case class AnusvaaraTests(
                           canonical_source: Option[String],
                           valid_variants: Seq[VariantPair]
                         )

class AnusvaaraVariantTestSpec extends AnyFunSuite {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  test("getNonAnusvaaraVariant provide the expected variant for certain cases") {
    val source = Source.fromResource("anusvaaraTests.json")
    implicit val formats: DefaultFormats.type = DefaultFormats
    val anusvaaraTests = Serialization.read[AnusvaaraTests](source.mkString)
    log.debug(anusvaaraTests.toString)

    log.info(s"Checking ${anusvaaraTests.valid_variants.size} strings.")
    anusvaaraTests.valid_variants.foreach((variantPair: VariantPair) => {
      val nonAnusvaaraVariant = transliterator.getNonAnusvaaraVariant(variantPair.form_with_anusvaara)
      log.debug(variantPair.toString)
      assert(nonAnusvaaraVariant == variantPair.form_without_anusvaara)
    })

  }

}