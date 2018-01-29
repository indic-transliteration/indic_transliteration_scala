package sanskritnlp.transliteration.indic

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
    'ळ' -> 'ଳ',
    'ड़' -> 'ଡ଼', 'ਫ਼' -> 'ଢ଼', 'य़' -> 'ୟ',
    '़' -> '଼',
    '्' -> '୍', 'ं' -> 'ଂ',  'ः' -> 'ଃ', 'ँ' -> 'ଁ',
    'ऽ' -> 'ଽ',
    '०' -> '୦', '१'-> '୧', '२'-> '୨',
    '३'-> '୩', '४'-> '୪', '५'-> '୫',
    '६'-> '୬', '७'-> '୭', '८'-> '୮', '९'-> '୯',
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.filterKeys(!Seq('ॆ', 'ॊ', 'ऎ', 'ऒ').contains(_)).map(_.swap) ++
    Map('ୱ' /* wa - non-devanAgarI*/ -> 'व')
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object bengali extends NativeIndicScript {
  /*
  rô is represented as র in Bengali, and as ৰ in Assamese. Assamese script has a character, wô, represented as ৱ; in Bengali it was represented as ব, same as bô, and was called অন্তঃস্থ ব ôntôsthô bô, to differentiate it from the other bô called বর্গীয় ব
   */

  // https://en.wikipedia.org/wiki/Bengali_(Unicode_block)
  override val mapFromDevanagari = Map(
    'अ' -> 'অ', 'आ' -> 'আ',  'इ' -> 'ই', 'ई' -> 'ঈ',
    'उ' -> 'উ', 'ऊ' -> 'ঊ',
    'ऋ' -> 'ঋ', 'ॠ' -> 'ৠ', 'ऌ' -> 'ঌ', 'ॡ' -> 'ৡ',
    'ऎ' -> 'এ', /*Reuse dIrgha*/
    'ए' -> 'এ',
    'ऐ' -> 'ঐ',
    'ऒ' -> 'ও', /*Reuse dIrgha*/
    'ओ' -> 'ও', 'औ' -> 'ঔ',
    'ा' -> 'া',
    'ि' -> 'ি',
    'ी' -> 'ী',
    'ु' -> 'ু', 'ू' -> 'ূ',
    'ृ' -> 'ৃ', 'ॄ' -> 'ৄ',
    'ॢ' -> 'ৢ', 'ॣ' -> 'ৣ',
    'ॆ' -> 'ে', /*Reuse dIrgha*/
    'े' -> 'ে',
    'ै' ->  'ৈ',
    'ॊ' -> 'ো', /*Reuse dIrgha*/
    'ो' -> 'ো',  'ौ' -> 'ৌ',
    'ह' -> 'হ', 'य' -> 'য', 'व' -> 'ব' /*Reuse ba*/, 'र' -> 'র', 'ल' -> 'ল',
    'ञ' -> 'ঞ',
    'ङ' -> 'ঙ',
    'म' -> 'ম',
    'ण' -> 'ণ',
    'न' -> 'ন',
    'झ' -> 'ঝ', 'भ' -> 'ভ',
    'घ' -> 'ঘ', 'ढ' -> 'ঢ', 'ध' -> 'ধ',
    'ज' -> 'জ', 'ब' -> 'ব', 'ग' -> 'গ',
    'ड' -> 'ড', 'द' -> 'দ',
    'ख' -> 'খ',
    'फ' -> 'ফ', 'छ' -> 'ছ', 'ठ' -> 'ঠ',
    'थ' -> 'থ', 'च' -> 'চ', 'ट' -> 'ট', 'त' -> 'ত',
    'क' -> 'ক', 'प' -> 'প',
    'श' -> 'শ', 'ष' -> 'ষ', 'स' -> 'স',
    // No 'ळ' -> 'ળ',
    'ड़' -> 'ড়', 'ਫ਼' -> 'ঢ়', 'य़' -> 'য়',
    '्' -> '্', 'ं' -> 'ং',  'ः' -> 'ঃ', 'ँ' -> 'ঁ',
    'ऽ' -> 'ঽ', '़' -> '়',
    '०' -> '০', '१'-> '১', '२'-> '২',
    '३'-> '৩', '४'-> '৪', '५'-> '৫',
    '६'-> '৬', '७'-> '৭', '८'-> '৮', '९'-> '৯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.filterKeys(!Seq('ॆ', 'ॊ', 'ऎ', 'ऒ', 'व').contains(_)).map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object assamese extends NativeIndicScript {
  /*
  rô is represented as র in Bengali, and as ৰ in Assamese. Assamese script has a character, wô, represented as ৱ; in Bengali it was represented as ব, same as bô, and was called অন্তঃস্থ ব ôntôsthô bô, to differentiate it from the other bô called বর্গীয় ব
   */

  // https://en.wikipedia.org/wiki/Assamese_alphabet
  // https://en.wikipedia.org/wiki/Bengali_(Unicode_block)
  override val mapFromDevanagari = Map(
    'अ' -> 'অ', 'आ' -> 'আ',  'इ' -> 'ই', 'ई' -> 'ঈ',
    'उ' -> 'উ', 'ऊ' -> 'ঊ',
    'ऋ' -> 'ঋ', 'ॠ' -> 'ৠ', 'ऌ' -> 'ঌ', 'ॡ' -> 'ৡ',
    'ऎ' -> 'এ', /*Reuse dIrgha*/
    'ए' -> 'এ',
    'ऐ' -> 'ঐ',
    'ऒ' -> 'ও', /*Reuse dIrgha*/
    'ओ' -> 'ও', 'औ' -> 'ঔ',
    'ा' -> 'া',
    'ि' -> 'ি',
    'ी' -> 'ী',
    'ु' -> 'ু', 'ू' -> 'ূ',
    'ृ' -> 'ৃ', 'ॄ' -> 'ৄ',
    'ॢ' -> 'ৢ', 'ॣ' -> 'ৣ',
    'ॆ' -> 'ে', /*Reuse dIrgha*/
    'े' -> 'ে',
    'ै' ->  'ৈ',
    'ॊ' -> 'ো', /*Reuse dIrgha*/
    'ो' -> 'ো',  'ौ' -> 'ৌ',
    'ह' -> 'হ', 'य' -> 'য', 'व' -> 'ব', 'र' -> 'ৰ', 'ल' -> 'ল',
    'ञ' -> 'ঞ',
    'ङ' -> 'ঙ',
    'म' -> 'ম',
    'ण' -> 'ণ',
    'न' -> 'ন',
    'झ' -> 'ঝ', 'भ' -> 'ভ',
    'घ' -> 'ঘ', 'ढ' -> 'ঢ', 'ध' -> 'ধ',
    'ज' -> 'জ', 'ब' -> 'ৱ', 'ग' -> 'গ',
    'ड' -> 'ড', 'द' -> 'দ',
    'ख' -> 'খ',
    'फ' -> 'ফ', 'छ' -> 'ছ', 'ठ' -> 'ঠ',
    'थ' -> 'থ', 'च' -> 'চ', 'ट' -> 'ট', 'त' -> 'ত',
    'क' -> 'ক', 'प' -> 'প',
    'श' -> 'শ', 'ष' -> 'ষ', 'स' -> 'স',
    // No 'ळ' -> 'ળ',
    'ड़' -> 'ড়', 'ਫ਼' -> 'ঢ়', 'य़' -> 'য়',
    '्' -> '্', 'ं' -> 'ং',  'ः' -> 'ঃ', 'ँ' -> 'ঁ',
    'ऽ' -> 'ঽ', '़' -> '়',
    '०' -> '০', '१'-> '১', '२'-> '২',
    '३'-> '৩', '४'-> '৪', '५'-> '৫',
    '६'-> '৬', '७'-> '৭', '८'-> '৮', '९'-> '৯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.filterKeys(!Seq('ॆ', 'ॊ', 'ऎ', 'ऒ').contains(_)).map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}
