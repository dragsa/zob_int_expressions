import expr._
import spreadsheet._
import org.scalatest._

class ParserTest extends FlatSpec with Matchers {

  def fixture = new {
    val spreadSheet = new SpreadSheet()
  }

  val one: Expr = Constant(1)
  val two: Expr = Constant(2)
  val three: Expr = Constant(3)
  val sum13: Expr = Add(three, one)
  val mul23: Expr = Mul(two, three)

  "3 + 1" should "equal 4 when the expression is parsed from string" in {
    val fix = fixture
    implicit val firstAddition = fix.spreadSheet
    val expr: Expr = Expr.fromString("+ 3 1")
    expr.value should be (4)
  }

  "4 + 3 + 1 * 6" should "equal 13 when the expression is parsed from string" in {
    val fix = fixture
    implicit val firstAddition = fix.spreadSheet
    val expr: Expr = Expr.fromString("+ * 6 1 + 3 4")
    expr.value should be (13)
  }

  "3 + 1 * 4" should "equal 7 when the expression is parsed from string" in {
    val fix = fixture
    implicit val firstAddition = fix.spreadSheet
    val expr: Expr = Expr.fromString("+ 3 * 1 4")
    expr.value should be (7)
  }

  "(3 + 1) * (4 + 3)" should "equal 28 when the expression is parsed from string" in {
    val fix = fixture
    implicit val firstAddition = fix.spreadSheet
    val expr: Expr = Expr.fromString("* + 3 1 + 4 3")
    expr.value should be (28)
  }


  "3 + 1 * a2" should "equal 6 when the expression is parsed from string and a2 set to 3" in {
    val fix = fixture
    implicit val firstAddition = fix.spreadSheet.add(Position('a', 2), three)
    val expr: Expr = Expr.fromString("+ 3 * 1 a2")
    expr.value should be (6)
  }

  "a1 + a2" should "equal 5 when the expression is parsed from string, both a2 and a1 set" in {
    val fix = fixture
    implicit val firstAddition = fix.spreadSheet.add(Position('a', 2), three).add(Position('a', 1), two)
    val expr: Expr = Expr.fromString("+ a1 a2")
    expr.value should be (5)
  }
}
