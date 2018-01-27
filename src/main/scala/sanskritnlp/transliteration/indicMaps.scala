package sanskritnlp.transliteration

import sanskritnlp.vyAkaraNa.devanAgarI

trait IndicScript {
  def fromDevanagari(str: String): String = null
  def toDevanagari(str: String): String = null
}

trait NativeIndicScript extends IndicScript {
  val mapFromDevanagari: Map[Char, Char] = null

  val mapToDevanagari: Map[Char, Char] = null

  val distinctCharacters: Set[Char] = null

  def isEncoding(str_in: String): Boolean = {
    return str_in.map(x => distinctCharacters.contains(x)).contains(true)
  }

  override def fromDevanagari(str: String): String = str.map(x => mapFromDevanagari.getOrElse(x, x)).mkString("")

  override def toDevanagari(str: String) = str.map(x => mapToDevanagari.getOrElse(x, x)).mkString("")

  def test = {
    val devanAgarI_str = devanAgarI.allSymbols.mkString("-")
    println(devanAgarI_str)
    println(fromDevanagari(devanAgarI_str))
  }
}

object kannaDa extends NativeIndicScript{
  // Unicode chars copied from kn-itrans.mim
  // http://www.koders.com/noncode/fid696F9AB94B6D1DB0A554AFA7D5E5C07F132E6CF9.aspx
  // Links for other languages *-itrans.mim are also found there.

  // ACKNOWLEDGEMENT:
  // The kannaDa table was originally created.
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).

  override val mapFromDevanagari = Map(
  'अ' -> 'ಅ', 'आ' -> 'ಆ',  'इ' -> 'ಇ', 'ई' -> 'ಈ',
  'उ' -> 'ಉ', 'ऊ' -> 'ಊ',
  'ऋ' -> 'ಋ', 'ॠ' -> 'ೠ', 'ऌ' -> 'ಌ', 'ॡ' -> 'ೡ',
  'ऎ' -> 'ಎ',
  'ए' -> 'ಏ',
  'ऐ' -> 'ಐ',
  'ऒ' -> 'ಒ',
  'ओ' -> 'ಓ', 'औ' -> 'ಔ',
  'ा' -> 'ಾ',
  'ि' -> 'ಿ',
  'ी' -> 'ೀ',
  'ु' -> 'ು', 'ू' -> 'ೂ',
  'ृ' -> 'ೃ', 'ॄ' -> 'ೄ',
  'ॢ' -> 'ೢ', 'ॣ' -> 'ೣ',
  'ॆ' -> 'ೆ',
  'े' -> 'ೇ',
  'ै' ->  'ೈ',
  'ॊ' -> 'ೊ',
  'ो' -> 'ೋ',  'ौ' -> 'ೌ',
  'ह' -> 'ಹ', 'य' -> 'ಯ', 'व' -> 'ವ', 'र' -> 'ರ', 'ल' -> 'ಲ',
  'ञ' -> 'ಞ',
  'ङ' -> 'ಙ',
  'म' -> 'ಮ',
  'ण' -> 'ಣ',
  'न' -> 'ನ',
  'झ' -> 'ಝ', 'भ' -> 'ಭ',
  'घ' -> 'ಘ', 'ढ' -> 'ಢ', 'ध' -> 'ಧ',
  'ज' -> 'ಜ', 'ब' -> 'ಬ', 'ग' -> 'ಗ',
  'ड' -> 'ಡ', 'द' -> 'ದ',
  'ख' -> 'ಖ',
  'फ' -> 'ಫ', 'छ' -> 'ಛ', 'ठ' -> 'ಠ',
  'थ' -> 'ಥ', 'च' -> 'ಚ', 'ट' -> 'ಟ', 'त' -> 'ತ',
  'क' -> 'ಕ', 'प' -> 'ಪ',
  'श' -> 'ಶ', 'ष' -> 'ಷ', 'स' -> 'ಸ',
  'ळ' -> 'ಳ', '्' -> '್', 'ं' -> 'ಂ',  'ः' -> 'ಃ',
  'ऽ' -> 'ಽ', '़' -> '಼',
  '०' -> '೦', '१'-> '೧', '२'-> '೨',
  '३'-> '೩', '४'-> '೪', '५'-> '೫',
  '६'-> '೬', '७'-> '೭', '८'-> '೮', '९'-> '೯'
  )

  override val mapToDevanagari = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.toSet


}

object telugu extends NativeIndicScript{
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).

  override val mapFromDevanagari = Map(
    'अ' -> 'అ', 'आ' -> 'ఆ',  'इ' -> 'ఇ', 'ई' -> 'ఈ',
    'उ' -> 'ఉ', 'ऊ' -> 'ఊ',
    'ऋ' -> 'ఋ', 'ॠ' -> 'ౠ', 'ऌ' -> 'ఌ', 'ॡ' -> 'ఌ',
    'ऎ' -> 'ఎ',
    'ए' -> 'ఏ',
    'ऐ' -> 'ఐ',
    'ऒ' -> 'ఒ',
    'ओ' -> 'ఓ', 'औ' -> 'ఔ',
    'ा' -> 'ಾ',
    'ि' -> 'ಿ',
    'ी' -> 'ೀ',
    'ु' -> 'ು', 'ू' -> 'ೂ',
    'ृ' -> 'ೃ', 'ॄ' -> 'ೄ', 'ॣ' -> 'ೄ',
    'ॢ' -> 'ೄ', 'ॆ' -> 'ೆ',
    'े' -> 'ೇ',
    'ै' ->  'ೈ',
    'ॊ' -> 'ೊ',
    'ो' -> 'ೋ',  'ौ' -> 'ೌ',
    'ह' -> 'హ', 'य' -> 'య', 'व' -> 'వ', 'र' -> 'ర', 'ल' -> 'ల',
    'ञ' -> 'ఞ',
    'ङ' -> 'ఙ',
    'म' -> 'మ',
    'ण' -> 'ణ',
    'न' -> 'న',
    'झ' -> 'ఝ', 'भ' -> 'భ',
    'घ' -> 'ఘ', 'ढ' -> 'ఢ', 'ध' -> 'ధ',
    'ज' -> 'జ', 'ब' -> 'బ', 'ग' -> 'గ',
    'ड' -> 'డ', 'द' -> 'ద',
    'ख' -> 'ఖ',
    'फ' -> 'ఫ', 'छ' -> 'ఛ', 'ठ' -> 'ఠ',
    'थ' -> 'థ', 'च' -> 'చ', 'ट' -> 'ట', 'त' -> 'త',
    'क' -> 'క', 'प' -> 'ప',
    'श' -> 'శ', 'ष' -> 'ష', 'स' -> 'స',
    'ळ' -> 'ళ', '्' -> '್', 'ं' -> 'ం',  'ः' -> 'ః',
    'ऽ' -> 'ఽ', '़' -> '़', 'ँ' -> 'ఁ',
    '०' -> '౦', '१'-> '౧', '२'-> '౨',
    '३'-> '౩', '४'-> '౪', '५'-> '౫',
    '६'-> '౬', '७'-> '౭', '८'-> '౮', '९'-> '౯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters = mapFromDevanagari.values.toSet


}


object gurmukhi extends NativeIndicScript{
  // Compare with http://bazaar.launchpad.net/~vinodh-vinodh/aksharamukha/trunk/view/head:/diCrunch/diCrunch_punjabi.php

  // gurmikhI lacks glyphs for several devanAgarI marks.
  // For hrasva e and o, we just use the dIrgha glyphs.
  // Elsewhere, for R, RR, L, LL, we just use the devanAgarI glyph.

  // gurmikhI has some glyphs which devanAgarI lacks. There, we just retain them.

  override val mapFromDevanagari = Map(
    'अ' -> 'ਅ', 'आ' -> 'ਆ',  'इ' -> 'ਇ', 'ई' -> 'ਈ',
    'उ' -> 'ਉ', 'ऊ' -> 'ਊ',
    'ऋ' -> 'ऋ', 'ॠ' -> 'ॠ', 'ऌ' -> 'ఌ', 'ॡ' -> 'ఌ',
    'ऎ' -> 'ਏ',
    'ए' -> 'ਏ',
    'ऐ' -> 'ਐ',
    'ऒ' -> 'ਓ',
    'ओ' -> 'ਓ', 'औ' -> 'ਔ',
    'ा' -> 'ਾ',
    'ि' -> 'ਿ',
    'ी' -> 'ੀ',
    'ु' -> 'ੁ', 'ू' -> 'ੂ',
    'ृ' -> 'ृ', 'ॄ' -> 'ॄ', 'ॣ' -> 'ॣ',
    'ॢ' -> 'ॢ', 'ॆ' -> 'ੇ',
    'े' -> 'ੇ',
    'ै' ->  'ੈ',
    'ॊ' -> 'ੋ',
    'ो' -> 'ੋ',  'ौ' -> 'ੌ',
    'ह' -> 'ਹ', 'य' -> 'ਯ', 'व' -> 'ਵ', 'र' -> 'ਰ', 'ल' -> 'ਲ',
    'ञ' -> 'ਞ',
    'ङ' -> 'ਙ',
    'म' -> 'ਮ',
    'ण' -> 'ਣ',
    'न' -> 'ਨ',
    'झ' -> 'ਝ', 'भ' -> 'ਭ',
    'घ' -> 'ਘ', 'ढ' -> 'ਢ', 'ध' -> 'ਧ',
    'ज' -> 'ਜ', 'ब' -> 'ਬ', 'ग' -> 'ਗ',
    'ड' -> 'ਡ', 'द' -> 'ਦ',
    'ख' -> 'ਖ',
    'फ' -> 'ਫ', 'छ' -> 'ਛ', 'ठ' -> 'ਠ',
    'थ' -> 'ਥ', 'च' -> 'ਚ', 'ट' -> 'ਟ', 'त' -> 'ਤ',
    'क' -> 'ਕ', 'प' -> 'ਪ',
    'ख़' -> 'ਖ਼', 'ग़' -> 'ਗ਼',
    'ड़' -> 'ੜ' /*.DA or .RHA*/, 'फ़' -> 'ਫ਼',
    '़' -> '़',
    'ੜ' -> 'ਕ', 'ਫ਼' -> 'ਪ',
    'श' -> 'ਸ਼', 'ष' -> 'ਸ਼', 'स' -> 'ਸ',
    'ळ' -> 'ਲ਼', '्' -> '್', 'ं' -> 'ਂ',  'ः' -> 'ਃ',
    'ऽ' -> 'ఽ', '़' -> '़', 'ँ' -> 'ਁ',
    '०' -> '੦', '१'-> '੧', '२'-> '੨',
    '३'-> '੩', '४'-> '੪', '५'-> '੫',
    '६'-> '੬', '७'-> '੭', '८'-> '੮', '९'-> '੯',
    'ॐ' -> 'ੴ', '॑' -> 'ੑ', /*udAtta*/
    'ੰ' -> 'ੰ' /*Tippi*/, 'ੱ' -> 'ੱ' /*aDDak*/, 'ੵ' -> 'ੵ', /*yakaSh*/
    'ੲ' -> 'ੲ' /*ura*/, 'ੳ' -> 'ੳ' /*iri*/
  )

  override val mapToDevanagari = mapFromDevanagari.map(_.swap)
  override val distinctCharacters = mapFromDevanagari.values.toSet

}

