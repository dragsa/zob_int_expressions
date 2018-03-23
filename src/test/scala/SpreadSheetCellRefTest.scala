import expr._
import spreadsheet._
import org.scalatest._

class SpreadSheetCellRefTest extends FlatSpec with Matchers {
  "3 + 1" should "equal 4 when evalutated in a spreadsheet with cell references" in {
    var spreadSheet =  // Create spreadsheet
    val three: Expr = // Create a constant Expr.
    val one: Expr = // Create a constant Expr here.
    // Put three and one in the spreadsheet, ad positions ('a',1) and ('a', 2)
    val refThree: Expr = // Create a ref to ('a', 1)
    val refOne: Expr = // Create a ref to ('a', 2)
    // Create an add expr of refThree and refOne, and put them in ('a',3) in the spreadsheet
    spreadSheet.eval(Position('a', 3)) should be (Some(4))
  }
}
