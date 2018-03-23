package spreadsheet

import expr.{Constant, Expr}

case class SpreadSheet(storage: Map[Position, Expr] = Map()) {

  def add(pos: Position, expr: Expr): SpreadSheet = {
    SpreadSheet(storage.updated(pos, expr))
  }

  def remove(pos: Position) = SpreadSheet(storage.get(pos) match {
    case Some(_) => storage - pos
    case None => storage
  })

  def eval(pos: Position) = storage.get(pos).flatMap(expr => Option(expr.value))

  def get(pos: Position) = storage.get(pos)

}

object SpreadSheet {
  implicit def posToVal(pos: Position)(implicit sd: SpreadSheet): Expr = {
    implicitly[SpreadSheet].get(pos).getOrElse(Constant(0))
  }
}

case class Position(column: Char, row: Int)
