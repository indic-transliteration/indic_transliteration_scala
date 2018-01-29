package sanskritnlp.transliteration.roman

object asTest {
  def main(args: Array[String]): Unit = {
    as.test_toDevanagari()
    as.test_fromDevanagari()
    println(as.distinctCharacters)
    println(as.distinctCharacters.map(x => iast.fromDevanagari(as.toDevanagari(x))) )
  }
}
