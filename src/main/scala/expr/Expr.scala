package expr

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
  val ops = List('*', '+')

  def fromString(str: String) = {
    ???
  }
}


