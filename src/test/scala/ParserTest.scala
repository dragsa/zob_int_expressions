import expr._
import spreadsheet._
import org.scalatest._

class ParserTest extends FlatSpec with Matchers {
  "3 + 1" should "equal 4 when the expression is parsed from string" in {
    /*  You might need to pass a spreadsheet as an argument to the parser, depending
        how you handle cell references.
     */
    val expr: Expr = Expr.fromString("+ 3 1")
    expr.value() should be (4)
  }

  "3 + 1 * 4" should "equal 4 when the expression is parsed from string" in {
    /*  You might need to pass a spreadsheet as an argument to the parser, depending
        how you handle cell references.
     */
    val expr: Expr = Expr.fromString("+ 3 * 1 4")
    expr.value() should be (4)
  }
}
