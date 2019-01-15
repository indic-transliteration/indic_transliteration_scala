package sanskritnlp.transliteration.indic


object kannada extends NativeIndicScript{
  // Unicode chars copied from kn-itrans.mim
  // http://www.koders.com/noncode/fid696F9AB94B6D1DB0A554AFA7D5E5C07F132E6CF9.aspx
  // Links for other languages *-itrans.mim are also found there.

  // ACKNOWLEDGEMENT:
  // The kannaDa table was originally created.
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).

  override val mapFromDevanagari: Map[Char, Char] = Map(
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
    'ळ' -> 'ಳ',  'ऱ' -> 'ಱ', 'ऴ' -> 'ೞ', '्' -> '್',
    'ᳵ' -> 'ೱ', 'ᳶ' -> 'ೲ',
    'ं' -> 'ಂ',  'ः' -> 'ಃ', 'ँ' -> 'ಁ',
    'ऽ' -> 'ಽ', '़' -> '಼',
    '०' -> '೦', '१'-> '೧', '२'-> '೨',
    '३'-> '೩', '४'-> '೪', '५'-> '೫',
    '६'-> '೬', '७'-> '೭', '८'-> '೮', '९'-> '೯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet


}

object telugu extends NativeIndicScript{
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  // Refer to https://en.wikipedia.org/wiki/Telugu_(Unicode_block)

  // We just use the kannada L and LL glyphs.

  override val mapFromDevanagari: Map[Char, Char] = Map(
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
    'ळ' -> 'ళ', '्' -> '్', 'ं' -> 'ం',  'ः' -> 'ః',
    'ऽ' -> 'ఽ',
    // '़' -> '़', No Nukta
    'ँ' -> 'ఁ',
    '०' -> '౦', '१'-> '౧', '२'-> '౨',
    '३'-> '౩', '४'-> '౪', '५'-> '౫',
    '६'-> '౬', '७'-> '౭', '८'-> '౮', '९'-> '౯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}


object malayalam extends NativeIndicScript{
  // https://en.wikipedia.org/wiki/Malayalam_(Unicode_block)
  override val mapFromDevanagari: Map[Char, Char] = Map(
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
    'ळ' -> 'ള', 'ऴ' -> 'ഴ', 'ऱ' -> 'റ',
    '्' -> '്', 'ं' -> 'ം',  'ः' -> 'ഃ', 'ँ' -> 'ഁ',
    'ऽ' -> 'ഽ',
    '०' -> '൦', '१'-> '൧', '२'-> '൨',
    '३'-> '൩', '४'-> '൪', '५'-> '൫',
    '६'-> '൬', '७'-> '൭', '८'-> '൮', '९'-> '൯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] =mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object sinhala extends NativeIndicScript{
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  // Refer to https://en.wikipedia.org/wiki/Sinhalese_alphabet

  override val mapFromDevanagari: Map[Char, Char] = Map(
  'ऄ'->'ඇ',
  'अ' -> 'අ', 'आ' -> 'ආ',  'इ' -> 'ඉ', 'ई' -> 'ඊ',
  'उ' -> 'උ', 'ऊ' -> 'ඌ',
  'ऋ' -> 'ඍ', 'ॠ' -> 'ඎ', 'ऌ' -> 'ඏ', 'ॡ' -> 'ඐ',
  'ऎ' -> 'එ',
  'ए' -> 'ඒ',
  'ऐ' -> 'ඓ',
  'ऒ' -> 'ඔ',
  'ओ' -> 'ඕ', 'औ' -> 'ඖ',
    'ऻ' -> 'ැ',
  'ा' -> 'ා',
  'ि' -> 'ි',
  'ी' -> 'ී',
  'ु' -> 'ු', 'ू' -> 'ූ',
  'ृ' -> 'ෘ', 'ॄ' -> 'ෲ', 'ॣ' -> 'ෟ',
  'ॢ' -> 'ෳ', 'ॆ' -> 'ෙ',
  'े' -> 'ේ',
  'ै' ->  'ෛ',
  'ॊ' -> 'ො',
  'ो' -> 'ෝ',  'ौ' -> 'ෞ',
  'ह' -> 'හ', 'य' -> 'ය', 'व' -> 'ව', 'र' -> 'ර', 'ल' -> 'ල',
  'ञ' -> 'ඤ',
  'ङ' -> 'ඞ',
  'म' -> 'ම',
  'ण' -> 'ණ',
  'न' -> 'න',
  'झ' -> 'ඣ', 'भ' -> 'භ',
  'घ' -> 'ඝ', 'ढ' -> 'ඪ', 'ध' -> 'ධ',
  'ज' -> 'ජ', 'ब' -> 'බ', 'ग' -> 'ග',
  'ड' -> 'ඩ', 'द' -> 'ද',
  'ख' -> 'ඛ',
  'फ' -> 'ඵ', 'छ' -> 'ඡ', 'ठ' -> 'ඨ',
  'थ' -> 'ථ', 'च' -> 'ච', 'ट' -> 'ට', 'त' -> 'ත',
  'क' -> 'ක', 'प' -> 'ප',
  'श' -> 'ශ', 'ष' -> 'ෂ', 'स' -> 'ස',
  'ळ' -> 'ළ',
    'फ़' ->  'ෆ',
  '्' -> '්', 'ं' -> 'ං',  'ः' -> 'ඃ',
  // 'ऽ' -> ''', No avagraha
  // '़' -> '़', No Nukta
  'ँ' -> 'ං', // Reuse anusvAra
  '०' -> '෦', '१'-> '෧', '२'-> '෨',
  '३'-> '෩', '४'-> '෪', '५'-> '෫',
  '६'-> '෬', '७'-> '෭', '८'-> '෮', '९'-> '෯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.filterKeys(!Seq('ँ').contains(_)).map(_.swap) ++
    Map('ඈ' -> 'आ', 'ෑ' -> 'ा', 'ඟ' -> 'ङ', 'ඬ' -> 'ण', 'ඳ' -> 'न', 'ඹ' -> 'म')
  val mapToDevanagariStrings: Map[String, String] = Map()
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}

object burmese extends NativeIndicScript{
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  // Refer to https://en.wikipedia.org/wiki/Burmese_alphabet

  // We just use the kannada L and LL glyphs.

  // TODO: Fix the below
  override val mapFromDevanagari: Map[Char, Char] = Map(
    'अ' -> ' ', 'आ' -> 'ာ',  'इ' -> 'ိ', 'ई' -> 'ီ',
  'उ' -> 'ု', 'ऊ' -> 'ူ',
  'ऋ' -> 'ၖ', 'ऌ' -> 'ၘ',
  'ए' -> 'ေ',
  'ऐ' -> 'ဲ',
//  'ओ' -> 'ော', 'औ' -> 'ော်', <-- String values
  'ा' -> 'ာ',
  'ि' -> 'ိ',
  'ी' -> 'ီ',
  'ु' -> 'ု', 'ू' -> 'ူ',
  'ृ' -> 'ၖ', 'ॄ' -> 'ၗ', 'ॣ' -> 'ೄ',
  'ॢ' -> 'ೄ', 'ॆ' -> 'ေ',
  'े' -> 'ေ',
  'ै' ->  'ဲ',
//  'ो' -> 'ော',  'ौ' -> 'ော်',
  'ह' -> 'ဟ', 'य' -> 'ယ', 'व' -> 'ဝ', 'र' -> 'ရ', 'ल' -> 'လ',
  'ञ' -> 'ဉ',
  'ङ' -> 'င',
  'म' -> 'မ',
  'ण' -> 'ဏ',
  'न' -> 'န',
  'झ' -> 'ဈ', 'भ' -> 'ဘ',
  'घ' -> 'ဃ', 'ढ' -> 'ဎ', 'ध' -> 'ဓ',
  'ज' -> 'ဇ', 'ब' -> 'ဗ', 'ग' -> 'ဂ',
  'ड' -> 'ဍ', 'द' -> 'ဒ',
  'ख' -> 'ခ',
  'फ' -> 'ဖ', 'छ' -> 'ဆ', 'ठ' -> 'ဌ',
  'थ' -> 'ထ', 'च' -> 'စ', 'ट' -> 'ဋ', 'त' -> 'တ',
  'क' -> 'က', 'प' -> 'ပ',
  'श' -> 'ၑ', 'ष' -> 'ၐ', 'स' -> 'သ',
  'ळ' -> 'ဠ', 'ं' -> 'ံ',  'ः' -> 'း',
    //  '्' -> ' ်', <--FIX
//  'ऽ' -> ''',
  // '़' -> '़', No Nukta
  'ँ' -> 'ံ',
  '०' -> '౦', '१'-> '౧', '२'-> '౨',
  '३'-> '౩', '४'-> '౪', '५'-> '౫',
  '६'-> '౬', '७'-> '౭', '८'-> '౮', '९'-> '౯'
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}


object thai extends NativeIndicScript{
  // Produced using shrI vinod rAjan's
  // akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  // There are many issues here- For starters, we should use string to string mapping for thai.


  override val mapFromDevanagari: Map[Char, Char] = Map(
//    'अ' -> ' ', 'आ' -> 'า',  'इ' -> 'ิ', 'ई' -> 'ี',
//  'उ' -> 'ุ', 'ऊ' -> 'ู',
////  'ऋ' -> 'ฺฤ', 'ॠ' -> 'ฺฤา', 'ऌ' -> 'ฺฦ', 'ॡ' -> 'ฺฦ',
////  'ऎ' -> เ'ะ',
////  'ए' -> เ'',
////  'ऐ' -> ไ'',
////  'ऒ' -> โ'ะ',
////  'ओ' -> โ'', 'औ' -> เ'า',
//  'ा' -> 'า',
//  'ि' -> 'ิ',
//  'ी' -> 'ี',
//  'ु' -> 'ุ', 'ू' -> 'ู',
//  'ृ' -> 'ฺฤ', 'ॄ' -> 'ฺฤา', 'ॣ' -> 'ೄ',
//  'ॢ' -> 'ೄ', 'ॆ' -> 'เะ',
//  'े' -> 'เ',
//  'ै' ->  'ไ',
//  'ॊ' -> 'โะ',
////  'ो' -> 'โ',  'ौ' -> 'เา',
//  'ह' -> 'ห', 'य' -> 'ย', 'व' -> 'ว', 'र' -> 'ร', 'ल' -> 'ล',
//  'ञ' -> 'ญ',
//  'ङ' -> 'ง',
//  'म' -> 'ม',
//  'ण' -> 'ณ',
//  'न' -> 'น',
//  'झ' -> 'ฌ', 'भ' -> 'ภ',
//  'घ' -> 'ฆ', 'ढ' -> 'ฒ', 'ध' -> 'ธ',
//  'ज' -> 'ช', 'ब' -> 'พ', 'ग' -> 'ค',
//  'ड' -> 'ฑ', 'द' -> 'ท',
//  'ख' -> 'ข',
//  'फ' -> 'ผ', 'छ' -> 'ฉ', 'ठ' -> 'ฐ',
//  'थ' -> 'ถ', 'च' -> 'จ', 'ट' -> 'ฏ', 'त' -> 'ต',
//  'क' -> 'ก', 'प' -> 'ป',
//  'श' -> 'ศ', 'ष' -> 'ษ', 'स' -> 'ส',
//  'ळ' -> 'ฬ', '्' -> '్', 'ं' -> 'ํ',
//  'ः' -> ':' // No visarga
  // 'ऽ' -> ''', NO avagraha
  // '़' -> '़', No Nukta
  )

  override val mapToDevanagari: Map[Char, Char] = mapFromDevanagari.map(_.swap)
  override val distinctCharacters: Set[Char] = mapToDevanagari.keys.filterNot(x => mapFromDevanagari.keys.toList.contains(x)).toSet
}
