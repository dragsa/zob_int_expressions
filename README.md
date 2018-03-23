# Scala Spreadsheets!

This challenge is about implementing simple spreadsheets in Scala. We have
created a list of subtasks that will gradually build a more advanced system. Try
to implement as many of the subtasks as you can, in the order we have listed them.

The project is built using sbt. To run all the test cases, run `sbt test` from
the terminal or the IDE of your choice.

## 1 - Expressions
Expressions have one operation, that is to evaluate them. The simplest
type of expresssion is the constant, which could be implemented as:

```
case class Constant(a: Long) {
  def value(): Long = a
}
```

Having only constant expressions is a bit useless, we want to be able to combine
them with operators as well. Those operators can also be expressions. To
accomplish that, you might need to use kind of polymorphism. For this task, we
want the expression types Constant, Add and Mul, where Add and Mul do addition
and multiplication of two expressions.

When this this task is finished, you should be able to fill out the details in
ExprTest.scala, and make it run. Feel free to write more tests as well.

# 2 - A SpreadSheet
A spreadsheet is something which lets you store an expression, in some position.
For this task, your positions can be a `Char` for the column, and an `Int` for
the row.

A spreadsheet should also allow you to evaluate the value of the expression in
some position of the spreadsheet.

When you have finished this task, you should be able to implement the test case
in SpreadSheetTest.scala.

# 3 - Cell References
For expressions and spreadsheets to be really useful together, we need to be
able to reference a cell in the spreadsheet from an expression. Conceptually,
you should be able to create the expression `2 + (the cell ('a',3))`, and get a
result if there is a value in that cell.

This task might require you to either change the type signature of your expressions,
or of the spreadsheet.

Once you have finished this task, you should be able to fill out the test case
in SpreadSheetCellRefTest.scala. Additionally, we want you to implement the
fibonacci sequence and factorial numbers recursively using cell references, in two
columns in the spreadsheet up to a finite row number. For example, if you store the
fibonacci sequence in column 'a', then evaluating the spreadsheet at position
('a', 10) should give the value 89.

# 4 - Expression Parsing
For this final task, we want you to build expressions from a string. For the
string representation of the expressions, you can choose between infix
operators, postfix operators, or prefix operators.

The tokens in the input string should be:

- *
- +
- integers
- Cell refs, which are a character followed by an integer, i.e `a10`

Once you have finished this task, you should be able to run ParserTest.scala.
