package ra.linearandconstant

import scala.annotation.tailrec

/**
 * @author Remus Amalinei
 */
object WellFormattedParenthesesString {

  def validate(input: String): Boolean = {
    if (input.isEmpty || containsNonParenthesis(input)) return false

    val OpenList = List('(', '[', '{')
    val ClosedOpenMap = Map(
      ')' -> '(',
      ']' -> '[',
      '}' -> '{')

    @tailrec
    def go(remainingInput: String, openParenthesisStack: List[Char]): Boolean = {
      if (remainingInput.isEmpty) return openParenthesisStack.isEmpty

      val currentChar = remainingInput(0)
      if (OpenList.contains(currentChar)) {
        go(remainingInput.substring(1), currentChar :: openParenthesisStack)
      } else if (openParenthesisStack.isEmpty) {
        false
      } else {
        val lastOpenParenthesis = openParenthesisStack.head

        if (lastOpenParenthesis == ClosedOpenMap(currentChar)) go(remainingInput.substring(1), openParenthesisStack.tail)
        else false
      }
    }

    go(input, List.empty)
  }

  private def containsNonParenthesis(input: String): Boolean = {
    val regex = """[^\(\[\{\)\]\}]""".r

    regex.findFirstIn(input) match {
      case Some(m) => true
      case None => false
    }
  }
}
