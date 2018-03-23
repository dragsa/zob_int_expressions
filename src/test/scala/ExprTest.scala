import expr._
import org.scalatest._

class ExprTest extends FlatSpec with Matchers {
  val one: Expr = Constant(1)
  val two: Expr = Constant(2)
  val three: Expr = Constant(3)

  "3 + 1" should "equal 4 when evaluated as an expression tree" in {
    val sum13: Expr = Add(three, one)
    sum13.value should be (4)
  }

  "(1 + 3) + (3 + 1)" should "equal 8 when evaluated as an expression tree" in {
    val sum1331 = Add(Add(one, three), Add(three, one))
    sum1331.value should be (8)
  }

  "2 * 3" should "equal 6 when evaluated as an expression tree" in {
    val mul23 = Mul(two, three)
    mul23.value should be (6)
  }

  "2 * 3 + 3" should "equal 9 when evaluated as an expression tree" in {
    val mul23sum3 = Add(Mul(two, three), three)
    mul23sum3.value should be (9)
  }
}
