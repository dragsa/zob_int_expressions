import expr._
import org.scalatest._
import spreadsheet._

class SpreadSheetCellRefTest extends FlatSpec with Matchers {

  def fixture = new {
    val spreadSheet = new SpreadSheet()
  }

  val one: Expr = Constant(1)
  val two: Expr = Constant(2)
  val three: Expr = Constant(3)
  val sum13: Expr = Add(three, one)
  val mul23: Expr = Mul(two, three)

  import SpreadSheet._

  "3 + 1" should "equal 4 when evaluated in a spreadsheet with cell references" in {
    val fix = fixture
    // Put three and one in the spreadsheet, ad positions ('a',1) and ('a', 2)
    // Create an add expr of refThree and refOne, and put them in ('a',3) in the spreadsheet
    // TODO this "last minute" implicit is dirty and not clear
    implicit val firstAddition = fix.spreadSheet.add(Position('a', 1), three).add(Position('a', 2), one)
    val thirdAddition = firstAddition.add(Position('a', 3), Add(Position('a', 1), Position('a', 2)))
    thirdAddition.eval(Position('a', 3)) should be(Some(4))
  }

  "sum of existing cell value with non-existing cell value" should "equal to existing cell value" in {
    val fix = fixture
    implicit val sumWithNonExisting = fix.spreadSheet.add(Position('a', 2), one)
    sumWithNonExisting.add(Position('a', 3), Add(Position('a', 1), Position('a', 2))).eval(Position('a', 3)) should be(Some(1))
  }

  "multiplication of existing cell value by non-existing cell value" should "equal 0" in {
    val fix = fixture
    implicit val multByNonExisting = fix.spreadSheet.add(Position('a', 2), one)
    multByNonExisting.add(Position('a', 3), Mul(Position('a', 1), Position('a', 2))).eval(Position('a', 3)) should be(Some(0))
  }

  "fibonacci for 10 values in single column" should "evaluate to expected values" in {
    val fix = fixture
    val fibonacciAddedSh = fibonacciCrawler(10, 'a', fix.spreadSheet)
    fibonacciAddedSh.eval(Position('a', 1)) should be(Some(1))
    fibonacciAddedSh.eval(Position('a', 9)) should be(Some(55))
    fibonacciAddedSh.eval(Position('a', 10)) should be(Some(89))
  }

  def fibonacciCrawler(n: Int, column: Char, sh: SpreadSheet) = {
    def fibonacciCrawlerHelper(nInner: Int, currentSh: SpreadSheet): SpreadSheet = {
      if (nInner > n) currentSh
      else if (nInner == 0 || nInner == 1) fibonacciCrawlerHelper(nInner + 1, currentSh.add(Position('a', nInner), Constant(1)))
      else {
        implicit val thisSpreadSheet = currentSh
        fibonacciCrawlerHelper(nInner + 1,
          currentSh.add(Position('a', nInner), Add(Position(column, nInner - 1), Position(column, nInner - 2))))
      }
    }

    fibonacciCrawlerHelper(0, sh)
  }
}
