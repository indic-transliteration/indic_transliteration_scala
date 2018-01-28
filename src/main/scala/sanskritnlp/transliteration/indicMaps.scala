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
    return str_in.exists(x => distinctCharacters.contains(x))
  }

  override def fromDevanagari(str: String): String = str.map(x => mapFromDevanagari.getOrElse(x, x)).mkString("")

  override def toDevanagari(str: String): String = str.map(x => mapToDevanagari.getOrElse(x, x)).mkString("")

  def test(): Unit = {
    val devanAgarI_str = devanAgarI.allSymbols.mkString("-")
    println(devanAgarI_str)
    println(fromDevanagari(devanAgarI_str))
  }
}

object devanagarii extends NativeIndicScript{
  override val distinctCharacters: Set[Char] = kannaDa.mapToDevanagari.values.toSet

  override def fromDevanagari(str: String): String = str

  override def toDevanagari(str: String): String = str
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
  'ळ' -> 'ಳ', '्' -> '್', 'ं' -> 'ಂ',  'ः' -> 'ಃ', 'ँ' -> 'ಁ',
  'ऽ' -> 'ಽ', '़' -> '಼',
  '०' -> '೦', '१'-> '೧', '२'-> '೨',
  '३'-> '೩', '४'-> '೪', '५'-> '೫',
  '६'-> '೬', '७'-> '೭', '८'-> '೮', '९'-> '೯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet


}

object telugu extends NativeIndicScript{
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  // Refer to https://en.wikipedia.org/wiki/Telugu_(Unicode_block)

  // We just use the kannada L and LL glyphs.

  override val mapFromDevanagari = Map(
    'अ' -> 'అ', 'आ' -> 'ఆ',  'इ' -> 'ఇ', 'ई' -> 'ఈ',
    'उ' -> 'ఉ', 'ऊ' -> 'ఊ',
    'ऋ' -> 'ఋ', 'ॠ' -> 'ౠ', 'ऌ' -> 'ఌ', 'ॡ' -> 'ఌ',
    'ऎ' -> 'ఎ',
    'ए' -> 'ఏ',
    'ऐ' -> 'ఐ',
    'ऒ' -> 'ఒ',
    'ओ' -> 'ఓ', 'औ' -> 'ఔ',
    'ा' -> 'ా',
    'ि' -> 'ి',
    'ी' -> 'ీ',
    'ु' -> 'ు', 'ू' -> 'ూ',
    'ृ' -> 'ృ', 'ॄ' -> 'ౄ', 'ॣ' -> 'ೄ',
    'ॢ' -> 'ೄ', 'ॆ' -> 'ె',
    'े' -> 'ే',
    'ै' ->  'ై',
    'ॊ' -> 'ొ',
    'ो' -> 'ో',  'ौ' -> 'ౌ',
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
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}


object gurmukhi extends NativeIndicScript{
  // Compare with http://bazaar.launchpad.net/~vinodh-vinodh/aksharamukha/trunk/view/head:/diCrunch/diCrunch_punjabi.php
  // and https://en.wikipedia.org/wiki/Gurmukhi_(Unicode_block)

  // gurmikhI lacks glyphs for several devanAgarI marks.
  // For hrasva e and o, we just use the dIrgha glyphs.
  // Elsewhere, for R, RR, L, LL, we just use the devanAgarI glyph.

  // gurmukhI has some glyphs which devanAgarI lacks. There, we just retain them.

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
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap) ++ Map('ੰ' /*Tippi - ਅਭੰਗ|अभੰग|abhanga*/ -> 'ं',
      'ੱ' -> 'ੱ' /*aDDak - causes duplication of subsequent consonant - ਅਕੱ|अकੱ|akka. Handled specially in fromDevanagari.*/, 'ੵ' -> 'ੵ', /*yakaSh*/
      'ੲ' -> 'ੲ' /*ura*/, 'ੳ' -> 'ੳ' /*iri*/)
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
  override def fromDevanagari(str: String): String = {
    val partialTransliteration = str.map(x => mapFromDevanagari.getOrElse(x, x)).mkString("")
    partialTransliteration
      .replaceAll("ੱ([कख])", "क्$1")
      .replaceAll("ੱ([गघ])", "ग्$1")
      .replaceAll("ੱ([चछ])", "च्$1")
      .replaceAll("ੱ([जझ])", "ज्$1")
      .replaceAll("ੱ([टठ])", "ट्$1")
      .replaceAll("ੱ([डढ])", "ड्$1")
      .replaceAll("ੱ([तथ])", "त्$1")
      .replaceAll("ੱ([दध])", "द्$1")
      .replaceAll("ੱ([पफ])", "प्$1")
      .replaceAll("ੱ([बभ])", "ब्$1")
      .replaceAll("ੱ([यरऱलळऴवशषसहङञणनऩमक़ख़ग़ज़ड़ढ़फ़य़])", "$1्$1")
  }
}

object oriya extends NativeIndicScript{
  // https://en.wikipedia.org/wiki/Oriya_(Unicode_block)

  override val mapFromDevanagari = Map(
    'अ' -> 'ଅ', 'आ' -> 'ଆ',  'इ' -> 'ଇ', 'ई' -> 'ଈ',
    'उ' -> 'ଉ', 'ऊ' -> 'ଊ',
    'ऋ' -> 'ଋ', 'ॠ' -> 'ୠ', 'ऌ' -> 'ଌ', 'ॡ' -> 'ୡ',
    'ऎ' -> 'ଏ',/* diirgha reused*/
    'ए' -> 'ଏ',
    'ऐ' -> 'ଐ',
    'ऒ' -> 'ଓ',/* diirgha reused*/
    'ओ' -> 'ଓ', 'औ' -> 'ଔ',
    'ा' -> 'ା',
    'ि' -> 'ି',
    'ी' -> 'ୀ',
    'ु' -> 'ୁ', 'ू' -> 'ୂ',
    'ृ' -> 'ୃ', 'ॄ' -> 'ୄ',
    'ॢ' -> 'ୢ', 'ॣ' -> 'ୣ',
    'ॆ' -> 'େ', /* diirgha reused*/
    'े' -> 'େ',
    'ै' ->  'ୈ',
    'ॊ' -> 'ୋ', /* diirgha reused*/
    'ो' -> 'ୋ',  'ौ' -> 'ୌ',
    'ह' -> 'ହ', 'य' -> 'ଯ', 'व' -> 'ଵ', 'र' -> 'ର', 'ल' -> 'ଲ',
    'ञ' -> 'ଞ',
    'ङ' -> 'ଙ',
    'म' -> 'ମ',
    'ण' -> 'ଣ',
    'न' -> 'ନ',
    'झ' -> 'ଝ', 'भ' -> 'ଭ',
    'घ' -> 'ଘ', 'ढ' -> 'ଢ', 'ध' -> 'ଧ',
    'ज' -> 'ଜ', 'ब' -> 'ବ', 'ग' -> 'ଗ',
    'ड' -> 'ଡ', 'द' -> 'ଦ',
    'ख' -> 'ଖ',
    'फ' -> 'ଫ', 'छ' -> 'ଛ', 'ठ' -> 'ଠ',
    'थ' -> 'ଥ', 'च' -> 'ଚ', 'ट' -> 'ଟ', 'त' -> 'ତ',
    'क' -> 'କ', 'प' -> 'ପ',
    'श' -> 'ଶ', 'ष' -> 'ଷ', 'स' -> 'ସ',
    'ळ' -> 'ଳ', '्' -> '୍', 'ं' -> 'ଂ',  'ः' -> 'ଃ', 'ँ' -> 'ଁ',
    'ऽ' -> 'ଽ', '़' -> '಼',
    '०' -> '୦', '१'-> '୧', '२'-> '୨',
    '३'-> '୩', '४'-> '୪', '५'-> '୫',
    '६'-> '୬', '७'-> '୭', '८'-> '୮', '९'-> '୯',
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.filterKeys(Seq('ॆ', 'ॊ', 'ऎ', 'ऒ').contains(_)).map(_.swap) ++ Map('ୱ' /* wa - non-devanAgarI*/ -> 'व')
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object malayalam extends NativeIndicScript{
  // https://en.wikipedia.org/wiki/Malayalam_(Unicode_block)
  override val mapFromDevanagari = Map(
    'अ' -> 'അ', 'आ' -> 'ആ',  'इ' -> 'ഇ', 'ई' -> 'ഈ',
    'उ' -> 'ഉ', 'ऊ' -> 'ഊ',
    'ऋ' -> 'ഋ', 'ॠ' -> 'ൠ', 'ऌ' -> 'ഌ', 'ॡ' -> 'ൡ',
    'ऎ' -> 'എ',
    'ए' -> 'ഏ',
    'ऐ' -> 'ഐ',
    'ऒ' -> 'ഒ',
    'ओ' -> 'ഓ', 'औ' -> 'ഔ',
    'ा' -> 'ാ',
    'ि' -> 'ി',
    'ी' -> 'ീ',
    'ु' -> 'ു', 'ू' -> 'ൂ',
    'ृ' -> 'ൃ', 'ॄ' -> 'ൄ',
    'ॢ' -> 'ൢ', 'ॣ' -> 'ൣ',
    'ॆ' -> 'െ',
    'े' -> 'േ',
    'ै' ->  'ൊ',
    'ॊ' -> 'ൈ',
    'ो' -> 'ോ',  'ौ' -> 'ൌ',
    'ह' -> 'ഹ', 'य' -> 'യ', 'व' -> 'വ', 'र' -> 'ര', 'ल' -> 'ല',
    'ञ' -> 'ഞ',
    'ङ' -> 'ങ',
    'म' -> 'മ',
    'ण' -> 'ണ',
    'न' -> 'ന',
    'झ' -> 'ഝ', 'भ' -> 'ഭ',
    'घ' -> 'ഘ', 'ढ' -> 'ഢ', 'ध' -> 'ധ',
    'ज' -> 'ജ', 'ब' -> 'ബ', 'ग' -> 'ഗ',
    'ड' -> 'ഡ', 'द' -> 'ദ',
    'ख' -> 'ഖ',
    'फ' -> 'ഫ', 'छ' -> 'ഛ', 'ठ' -> 'ഠ',
    'थ' -> 'ഥ', 'च' -> 'ച', 'ट' -> 'ട', 'त' -> 'ത',
    'क' -> 'ക', 'प' -> 'പ',
    'श' -> 'ശ', 'ष' -> 'ഷ', 'स' -> 'സ',
    'ळ' -> 'ള', '्' -> '്', 'ं' -> 'ം',  'ः' -> 'ഃ', 'ँ' -> 'ഁ',
    'ऽ' -> 'ഽ', '़' -> '಼',
    '०' -> '൦', '१'-> '൧', '२'-> '൨',
    '३'-> '൩', '४'-> '൪', '५'-> '൫',
    '६'-> '൬', '७'-> '൭', '८'-> '൮', '९'-> '൯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object gujarati extends NativeIndicScript{

  // https://en.wikipedia.org/wiki/Gujarati_(Unicode_block)
  override val mapFromDevanagari = Map(
    'अ' -> 'અ', 'आ' -> 'આ',  'इ' -> 'ઇ', 'ई' -> 'ઈ',
    'उ' -> 'ઉ', 'ऊ' -> 'ઊ',
    'ऋ' -> 'ઋ', 'ॠ' -> 'ૠ', 'ऌ' -> 'ઌ', 'ॡ' -> 'ૡ',
    'ऎ' -> 'ઍ',
    'ए' -> 'એ',
    'ऐ' -> 'ઐ',
    'ऒ' -> 'ઑ',
    'ओ' -> 'ઓ', 'औ' -> 'ઔ',
    'ा' -> 'ા',
    'ि' -> 'િ',
    'ी' -> 'ી',
    'ु' -> 'ુ', 'ू' -> 'ૂ',
    'ृ' -> 'ૃ', 'ॄ' -> 'ૄ',
    'ॢ' -> 'ૢ', 'ॣ' -> 'ૣ',
    'ॆ' -> 'ૅ',
    'े' -> 'ે',
    'ै' ->  'ૈ',
    'ॊ' -> 'ૉ',
    'ो' -> 'ો',  'ौ' -> 'ૌ',
    'ह' -> 'હ', 'य' -> 'ય', 'व' -> 'વ', 'र' -> 'ર', 'ल' -> 'લ',
    'ञ' -> 'ઞ',
    'ङ' -> 'ઙ',
    'म' -> 'મ',
    'ण' -> 'ણ',
    'न' -> 'ન',
    'झ' -> 'ઝ', 'भ' -> 'ભ',
    'घ' -> 'ઘ', 'ढ' -> 'ઢ', 'ध' -> 'ધ',
    'ज' -> 'જ', 'ब' -> 'બ', 'ग' -> 'ગ',
    'ड' -> 'ડ', 'द' -> 'દ',
    'ख' -> 'ખ',
    'फ' -> 'ફ', 'छ' -> 'છ', 'ठ' -> 'ઠ',
    'थ' -> 'થ', 'च' -> 'ચ', 'ट' -> 'ટ', 'त' -> 'ત',
    'क' -> 'ક', 'प' -> 'પ',
    'श' -> 'શ', 'ष' -> 'ષ', 'स' -> 'સ',
    'ळ' -> 'ળ', '्' -> '્', 'ं' -> 'ં',  'ः' -> 'ઃ', 'ँ' -> 'ઁ',
    'ऽ' -> 'ઽ', '़' -> '಼',
    '०' -> '૦', '१'-> '૧', '२'-> '૨',
    '३'-> '૩', '४'-> '૪', '५'-> '૫',
    '६'-> '૬', '७'-> '૭', '८'-> '૮', '९'-> '૯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapFromDevanagari.values.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}
