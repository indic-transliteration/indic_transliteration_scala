package sanskritnlp.vyAkaraNa

import scala.collection.{immutable, mutable}
import scala.collection.mutable.{ArrayBuffer, HashMap}

object shivasUtra {
  val sUtra = "अइउण् ऋऌक् एओङ् ऐऔच् हयवरट् लँण् ञमङणनम् झभञ् घढधष् जबगडदश् खफछठथचटतव् कपय् शषसर् हल्"
  val sUtra_groups: immutable.IndexedSeq[String] = sUtra.split(" ").toIndexedSeq

  // Letters representing indices, aka halitaH markers, anubandha
  // Eg: ण् क् ङ्. Note ण् is repeated.
  val halitaH: immutable.IndexedSeq[String] = sUtra_groups.map(x => x.takeRight(2).toString)

  // Eg: उ ऌ ओ. There is no ambiguity with this.
  val gaNAntimAkShara: immutable.IndexedSeq[String] = sUtra_groups.map(x => {
    if(x == "लँण्") "ल"
    else x.takeRight(3).head.toString
  })

  val itaH: immutable.IndexedSeq[String] = halitaH ++ List("अँ")
  
  var sUtra_symbols: String = sUtra.replaceAll(" ", "")
  // तस्य  लोपः॥ - तस्य इतो लोपः।
  itaH.foreach(y => {
    var x = y;
    if(x.last ==  'ँ') x = x.last.toString;
    sUtra_symbols = sUtra_symbols.replaceAll(x, "")
    })

  val itsaMkhyA: immutable.IndexedSeq[Int] = gaNAntimAkShara.map(x => sUtra_symbols.lastIndexOf(x)) ++ List(sUtra_symbols.indexOf("ल"))
  val saMkhyA_map: Map[String, Int] = (itaH zip itsaMkhyA).toMap

//   Example Input: pratyAhAra(start = "इ", end = "क्")
// Aka pratyAhAra
// Confidence in correctness: Low
// Reason: Well tested. Doesn't yet return all vowel forms.
  def get_symbols(start: String, endIn: String, bShortestMatch: Boolean = true): String = {
    println(start + endIn)
    val beginPos = sUtra_symbols.indexOf(start)
    val end  = if(endIn == "ँ") "अँ" else endIn
    var endPos = saMkhyA_map(end)+1
    if(end == "ण्" && bShortestMatch)
      endPos = sUtra_symbols.indexOf("उ") + 1

    val akSharas = sUtra_symbols.substring(beginPos, endPos)
    
    akSharas
  }

  // आदिरन्त्येन सहेता॥
  def pratyAhAra(start: String, end: String, bShortestMatch: Boolean): IndexedSeq[String] = {
    
    val symbols = get_symbols(start, end, bShortestMatch)
    // हकारादिष्वकार उच्चारणार्थः॥
    val groups = symbols.groupBy(x => consonants.vyanjana_symbols contains x)
    println(groups)
    var akSharAH = IndexedSeq[String]()
    if(groups.contains(true))
      akSharAH ++= groups(true).map(x => x.toString + vowels.virAma)
    if(groups.contains(false))
      akSharAH ++= groups(false).map(x => vowels.getAllForms(x.toString)).flatten
    akSharAH
  }

  def pratyAhAra(strIn: String, bShortestMatch: Boolean = true): IndexedSeq[String] = pratyAhAra(strIn(0).toString, strIn.substring(1), bShortestMatch)
  
  def test(): Unit = {
    println("sUtra_symbols" + sUtra_symbols)
    println(gaNAntimAkShara zip itsaMkhyA)
    println("halitaH " + halitaH.mkString(" "))
    println("sUtra_symbols " + sUtra_symbols.mkString(" "))
    println("saMkhyA_map " + saMkhyA_map)
    
    println("pratyAhAra अण् " + akSharasaMjJNA.fetch("अण्").mkString(" "))
    println("pratyAhAra इक् " + akSharasaMjJNA.fetch("इक्").mkString(" "))
    println("pratyAhAra हल् " + akSharasaMjJNA.fetch("हल्").mkString(" "))
    println("pratyAhAra रँ " + akSharasaMjJNA.fetch("रँ").mkString(" "))
  }
}

object akSharasaMjJNA {
  val map: mutable.HashMap[String, IndexedSeq[String]] = mutable.HashMap(
    // 'eShAm antyaa itaH'
    // लण्मध्ये त्वित्सञ्ज्ञकः॥
    // Their use as markers: हलन्त्यं॥ with अनुवृत्ति - उपदेशे हल्-अन्त्यं इत् स्यात्।
    "इत्" -> (shivasUtra.itaH),
    
    "हल्" -> shivasUtra.pratyAhAra("हर्"),
    
    "आत्" -> IndexedSeq("आ")
  )

//   वृद्धिरादैच्॥
  map += "वृद्धि" -> (map("आत्") ++ shivasUtra.pratyAhAra("ऐच्"))
  
  def fetch(strIn: String, bShortestMatch: Boolean = true) = {
    // अणादिसञ्ज्ञार्थानि।
    map.getOrElse(strIn, shivasUtra.pratyAhAra(strIn, bShortestMatch))
  }
}
