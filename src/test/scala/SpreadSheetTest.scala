import expr._
import org.scalatest._
import spreadsheet._

class SpreadSheetTest extends FlatSpec with Matchers {

  def fixture = new {
    val spreadSheet = new SpreadSheet()
  }

  val one: Expr = Constant(1)
  val two: Expr = Constant(2)
  val three: Expr = Constant(3)
  val sum13: Expr = Add(three, one)
  val mul23: Expr = Mul(two, three)

  "3 + 1" should "equal 4 when evaluated in a spreadsheet" in {
    val fix = fixture
    fix.spreadSheet.eval(Position('a', 3)) should be(None)
    fix.spreadSheet.add(Position('a', 3), sum13).eval(Position('a', 3)) should be(Some(4))
  }

  "3 * 2" should "equal 6 when evaluated in a spreadsheet" in {
    val fix = fixture
    fix.spreadSheet.eval(Position('a', 3)) should be(None)
    fix.spreadSheet.add(Position('a', 3), mul23).eval(Position('a', 3)) should be(Some(6))
  }

  "remove" should "remove expr element from position in spreadsheet if any" in {
    val fix = fixture
    fix.spreadSheet.eval(Position('a', 3)) should be(None)
    val addition = fix.spreadSheet.add(Position('a', 3), sum13)
    addition.eval(Position('a', 3)) should be(Some(4))
    addition.remove(Position('a', 3)).eval(Position('a', 3)) should be(None)
  }

}
