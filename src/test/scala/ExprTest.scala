import expr._
import org.scalatest._

class ExprTest extends FlatSpec with Matchers {
  "3 + 1" should "equal 4 when evalutated as an expression tree" in {
    val three: Expr = // Create a constant Expr here.
    val one: Expr = // Create a constant Expr here.
    val sum: Expr = // Create an add Expr here.
    sum.value should be (4)
  }
}
