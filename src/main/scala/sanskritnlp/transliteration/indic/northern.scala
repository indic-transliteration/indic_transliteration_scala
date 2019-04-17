package sanskritnlp.transliteration.indic

import org.slf4j.{Logger, LoggerFactory}

object gurmukhi extends NativeIndicScript{
  private val log: Logger = LoggerFactory.getLogger(this.getClass)
  // Compare with http://bazaar.launchpad.net/~vinodh-vinodh/aksharamukha/trunk/view/head:/diCrunch/diCrunch_punjabi.php
  // and https://en.wikipedia.org/wiki/Gurmukhi_(Unicode_block)

  // gurmikhI lacks glyphs for several devanAgarI marks.
  // For hrasva e and o, we just use the dIrgha glyphs.
  // Elsewhere, for R, RR, L, LL, we just use the devanAgarI glyph.

  // gurmukhI has some glyphs which devanAgarI lacks. There, we just retain them.

  override val mapFromDevanagari = Map(
    'अ' -> 'ਅ', 'आ' -> 'ਆ',  'इ' -> 'ਇ', 'ई' -> 'ਈ',
    'उ' -> 'ਉ', 'ऊ' -> 'ਊ',
    // 'ऋ' -> 'ऋ', 'ॠ' -> 'ॠ', 'ऌ' -> 'ऌ', 'ॡ' -> 'ॡ', /* devanAgarI reused */
    'ऎ' -> 'ਏ', /* dIrgha reused */
    'ए' -> 'ਏ',
    'ऐ' -> 'ਐ',
    'ऒ' -> 'ਓ', /* dIrgha reused */
    'ओ' -> 'ਓ', 'औ' -> 'ਔ',
    'ा' -> 'ਾ',
    'ि' -> 'ਿ',
    'ी' -> 'ੀ',
    'ु' -> 'ੁ', 'ू' -> 'ੂ',
    'ृ' -> 'ृ', 'ॄ' -> 'ॄ', 'ॣ' -> 'ॣ',
    'ॢ' -> 'ॢ',
    'ॆ' -> 'ੇ',
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
    'ख़' -> 'ਖ਼', 'ग़' -> 'ਗ਼', 'ज़' -> 'ਜ਼',
    'ड़' -> 'ੜ' /*.DA or .RHA*/, 'फ़' -> 'ਫ਼',
    '़' -> '਼',
    'ੜ' -> 'ਕ', 'ਫ਼' -> 'ਪ',
    'श' -> 'ਸ਼', 'ष' -> 'ਸ਼', /*Reusing sha*/ 'स' -> 'ਸ',
    'ळ' -> 'ਲ਼', '्' -> '੍', 'ं' -> 'ਂ',  'ः' -> 'ਃ',
    'ऽ' -> 'ऽ', 'ँ' -> 'ਁ',
    '०' -> '੦', '१'-> '੧', '२'-> '੨',
    '३'-> '੩', '४'-> '੪', '५'-> '੫',
    '६'-> '੬', '७'-> '੭', '८'-> '੮', '९'-> '੯',
    'ॐ' -> 'ੴ', '॑' -> 'ੑ', /*udAtta*/
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.filterKeys(!Seq('ॆ', 'ॊ', 'ऎ', 'ऒ').contains(_)).map(_.swap) ++
    Map('ੰ' /*Tippi - ਅਭੰਗ|अभੰग|abhanga*/ -> 'ं',
      'ੱ' -> 'ੱ' /*aDDak - causes duplication of subsequent consonant - ਅਕੱ|अकੱ|akka. Handled specially in toDevanagari.*/, 'ੵ' -> 'ੵ', /*yakaSh*/
      'ੲ' -> 'ੲ' /*ura*/, 'ੳ' -> 'ੳ' /*iri*/)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
  override def toDevanagari(str: String): String = {
    val partialTransliteration = str.map(x => mapToDevanagari.getOrElse(x, x)).mkString("")
    //    log.debug(partialTransliteration)
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
      // Note that the below includes glyphs with nukta as well.
      .replaceAll("ੱ([यरऱलळऴवशषसहङञणनऩमक़ख़ग़ज़ड़ढ़फ़य़])", "$1्$1")
      // At this point, only terminal ੱ will remain.
      // The below handles nuktas and various other dependent signs as well.
      .replaceAll("([कख])([ऺ-ॏऀ-ः]*?)ੱ", "क्$1")
      .replaceAll("([गघ])([ऺ-ॏऀ-ः]*?)ੱ", "ग्$1")
      .replaceAll("([चछ])([ऺ-ॏऀ-ः]*?)ੱ", "च्$1")
      .replaceAll("([जझ]([ऺ-ॏऀ-ः]*?))ੱ", "ज्$1")
      .replaceAll("([टठ])([ऺ-ॏऀ-ः]*?)ੱ", "ट्$1")
      .replaceAll("([डढ])([ऺ-ॏऀ-ः]*?)ੱ", "ड्$1")
      .replaceAll("([तथ])([ऺ-ॏऀ-ः]*?)ੱ", "त्$1")
      .replaceAll("([दध])([ऺ-ॏऀ-ः]*?)ੱ", "द्$1")
      .replaceAll("([पफ])([ऺ-ॏऀ-ः]*?)ੱ", "प्$1")
      .replaceAll("([बभ])([ऺ-ॏऀ-ः]*?)ੱ", "ब्$1")
      .replaceAll(s"([यरऱलळऴवशषसहङञणनऩमक़ख़ग़ज़ड़ढ़फ़य़])([ऺ-ॏऀ-ः]*?)ੱ", "$1्$1$2")
  }
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
    'ऽ' -> 'ઽ', '़' -> '઼',
    '०' -> '૦', '१'-> '૧', '२'-> '૨',
    '३'-> '૩', '४'-> '૪', '५'-> '૫',
    '६'-> '૬', '७'-> '૭', '८'-> '૮', '९'-> '૯',
    'ॐ' -> 'ૐ',
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object tibetan extends NativeIndicScript{
  // Produced using shrI vinod rAjan྅s
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  // Refer to https://en.wikipedia.org/wiki/Telugu(Unicodeblock)

  // We just use the kannada L and LL glyphs.

  override val mapFromDevanagari = Map(
  'अ' -> 'ཨ',
  'ु' -> 'ུ', 'ू' -> 'ཱུ',
  'ृ' -> 'ྲྀ', 'ॄ' -> 'ཷ', 'ॣ' -> 'ླྀ',
  'ॢ' -> 'ཹ',
  'े' -> 'ེ',
  'ै' -> 'ཻ',
  'ो' -> 'ོ', 'ौ' -> 'ཽ',
  'ह' -> 'ཧ', 'य' -> 'ཡ', 'व' -> 'ཝ', 'र' -> 'ར', 'ल' -> 'ལ',
  'ञ' -> 'ཉ',
  'ङ' -> 'ང',
  'म' -> 'མ',
  'ण' -> 'ཎ',
  'न' -> 'ན',
  'झ' -> 'ཛྷ', 'भ' -> 'བྷ',
  'घ' -> 'གྷ', 'ढ' -> 'ཌྷ', 'ध' -> 'དྷ',
  'ज' -> 'ཛ', 'ब' -> 'བ', 'ग' -> 'ག',
  'ड' -> 'ཌ', 'द' -> 'ད',
  'ख' -> 'ཁ',
  'फ' -> 'ཕ', 'छ' -> 'ཚ', 'ठ' -> 'ཋ',
  'थ' -> 'ཐ', 'च' -> 'ཙ', 'ट' -> 'ཊ', 'त' -> 'ཏ',
  'क' -> 'ཀ', 'प' -> 'པ',
  'श' -> 'ཤ', 'ष' -> 'ཥ', 'स' -> 'ས',
  'ळ' -> 'ལ', '्' -> '྄',
    'ं' -> 'ཾ', 'ः' -> 'ཿ',
    'ज़' -> 'ཟ',
//  'ऽ' -> ''',
  // '़' -> '़', No Nukta
  'ँ' -> 'ྃ',
  '०' -> '༠', '१'-> '༡', '२'-> '༢',
  '३'-> '༣', '४'-> '༤', '५'-> '༥',
  '६'-> '༦', '७'-> '༧', '८'-> '༨', '९'-> '༩'
  )

  val mapFromDevanagariToStrings: Map[Char, String] = Map(
    'आ' -> "ཨཱ", 'इ' -> "ཨི", 'ई' -> "ཨཱི",
  'उ' -> "ཨུ", 'ऊ' -> "ཨཱུ",
    'ऋ' -> "ཨྲྀ", 'ॠ' -> "ཨྲཱྀ", 'ऌ' -> "ཨླྀ", 'ॡ' -> "ཨླྀ",
  'ऎ' -> "ཨེ",
  'ए' -> "ཨེ",
  'ऐ' -> "ཨཻ",
  'ऒ' -> "ཨོ",
  'ओ' -> "ཨོ", 'औ' -> "ཨཽ",
  'ा' -> "ཱ",
  'ि' -> "ི",
  'ी' -> "ཱི"
  )

  // TODO : འ -A, ཀྵ KSa, ཪ RA, ཫ Ka, ཬ RRa
// Subjoined letters  ྐ	ྑ	ྒ	ྒྷ	ྔ	ྕ	ྖ	ྗ		ྙ	ྚ	ྛ	ྜ	ྜྷ	ྞ	ྟ
//  U+0FAx	ྠ	ྡ	ྡྷ	ྣ	ྤ	ྥ	ྦ	ྦྷ	ྨ	ྩ	ྪ	ྫ	ྫྷ	ྭ	ྮ	ྯ
//  U+0FBx	ྰ	ྱ	ྲ	ླ	ྴ	ྵ	ྶ	ྷ	ྸ	ྐྵ	ྺ	ྻ	ྼ
  // Pluta - ྅
  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap) ++
  Map(
    'ཅ' -> 'च','ཆ' -> 'छ', 'ཇ' -> 'ज',
    'ཞ' -> 'ज़')
  // TODO: Override from devanAgarI

  val mapTibetanStringToDevanagariString = Map(
    "ཇྷ" -> "झ"
  )
  // Override transliteration methods.
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}
