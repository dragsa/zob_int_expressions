import expr._
import spreadsheet._
import org.scalatest._

class SpreadSheetTest extends FlatSpec with Matchers {
  "3 + 1" should "equal 4 when evalutated in a spreadsheet" in {
    var spreadSheet = SpreadSheet()

    // Copy the sum expression from ExprTest.scala
    val three: Expr = // Create a constant Expr here.
    val one: Expr = // Create a constant Expr here.
    val sum: Expr = // Create an add Expr here.

    // add sum to spreadSheet at ('a', 3)
    spreadSheet.eval(Position('a', 3)) should be (Some(4))
  }
}
