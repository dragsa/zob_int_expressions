package expr

import spreadsheet.{Position, SpreadSheet}

sealed trait Expr {
  def value: Long
}

case class Constant(a: Long) extends Expr {
  def value: Long = a
}

case class Mul(a: Expr, b: Expr) extends Expr {
  def value: Long = a.value * b.value
}

case class Add(a: Expr, b: Expr) extends Expr {
  def value: Long = a.value + b.value
}

object Expr {

  import SpreadSheet._

  def fromString(str: String)(implicit sh: SpreadSheet) = {
    str.split(" ").toList.foldRight(List[Expr]())(
      (symbol, list) => (symbol, list) match {
        case ("+", x :: y :: rest) => Add(if (cellMatcher(x.value.toString)) cellSplitter(x.value.toString) else x, if (cellMatcher(y.value.toString)) cellSplitter(y.value.toString) else y) :: rest
        case ("*", x :: y :: rest) => Mul(if (cellMatcher(x.value.toString)) cellSplitter(x.value.toString) else x, if (cellMatcher(y.value.toString)) cellSplitter(y.value.toString) else y) :: rest
        case (_, _) => Constant(if (cellMatcher(symbol)) {
          sh.eval(cellSplitter(symbol)).getOrElse(0L)
        } else symbol.toLong) :: list
      }).head
  }

  private def cellMatcher(maybeCell: String) = {
    maybeCell.matches("[a-z][0-9]+")
  }

  private def cellSplitter(definitelyCell: String) = {
    val alphaPart = definitelyCell.takeWhile(Character.isLetter(_))
    Position(alphaPart(0), definitelyCell.drop(alphaPart.length).toInt)
  }
}


