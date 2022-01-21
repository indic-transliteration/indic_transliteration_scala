package sanskritnlp.transliteration.indic

import org.slf4j.{Logger, LoggerFactory}
import toml.Value.Tbl

import scala.collection.mutable
import scala.io.Source

trait IndicScript {
  def fromDevanagari(str: String): String = null
  def toDevanagari(str: String): String = null
  def getAlternatives(str: String): Seq[String] = Seq(str)
}

trait NativeIndicScript extends IndicScript {
  val mapFromDevanagari: Map[Char, Char] = null

  val mapToDevanagari: Map[Char, Char] = null

  val mapToDevanagariStrings: Map[String, String] = Map()

  val distinctCharacters: Set[Char] = null

  def isEncoding(str_in: String): Double = {
    str_in.map(x => if (distinctCharacters.contains(x)) 1.0 else 0).sum/Seq(str_in.length, 1.0).max /* No 0 denominator */
  }

  override def fromDevanagari(str: String): String = {
    var strToConvert = str
    mapToDevanagariStrings.map(_.swap)foreach({case (key: String, value: String) =>
      strToConvert = strToConvert.replaceAll(key, value)
    })
    strToConvert.map(x =>
      mapFromDevanagari.getOrElse(x, x)
    ).mkString("")
  }

  override def toDevanagari(str: String): String = {
    var strToConvert = str
    mapToDevanagariStrings.foreach({case (key: String, devanagariEquivalent: String) =>
      strToConvert = strToConvert.replaceAll(key, devanagariEquivalent)
    })
    strToConvert.map(x => mapToDevanagari.getOrElse(x, x)).mkString("")
  }
  
  //   setFromToml("script_maps/brahmic/gujarati.toml")
  def setFromToml(path: String) = {
    val tomlData = toml.Toml.parse((Source.fromResource(path).mkString)).right.get.values
    val mapFromDevanagariMutable = mutable.Map[Char, Char]()
    tomlData.keys.foreach(key => {
      val innerMap = tomlData(key).asInstanceOf[Tbl].values
      innerMap.keys.foreach(x => {
        mapFromDevanagariMutable.put(x.charAt(0), innerMap(x).asInstanceOf[String].charAt(0)) 
      })
    }) 
    // TODO: Make mapFromDevanagari etc.. a mutable String map.
//    shared_data.get("vowels")
  }
  
}


object devanaagarii extends NativeIndicScript{
  override val distinctCharacters: Set[Char] = kannada.mapToDevanagari.values.toSet

  override def fromDevanagari(str: String): String = str

  override def toDevanagari(str: String): String = str
}

object indicMaps {

  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main (args: Array[String] ): Unit = {
    log.debug(gurmukhi.fromDevanagari("छीड़ा चुदाकड़"))
  }
}