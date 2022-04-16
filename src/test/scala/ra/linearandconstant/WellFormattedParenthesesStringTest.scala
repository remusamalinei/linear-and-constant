package ra.linearandconstant

import ra.linearandconstant.WellFormattedParenthesesString.validate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author Remus Amalinei
 */
@RunWith(classOf[JUnitRunner])
class WellFormattedParenthesesStringTest extends FlatSpec with Matchers {

  "A string" should "be considered a well formatted parentheses string" in {
    val wfpStrings =
      Table("wfps", "()", "[{()}]", "[([{()}])]")

    forAll(wfpStrings) { wfps =>
      validate(wfps) should be(true)
    }
  }

  it should "not be considered a well formatted parentheses string if it is empty" in {
    validate("") should be(false)
  }

  it should "not be considered a well formatted parentheses string if it contains other characters" in {
    val notWfpStrings =
      Table("notWfps", "( )", "[A{()}]")

    forAll(notWfpStrings) { notWfps =>
      validate(notWfps) should be(false)
    }
  }

  it should "not be considered a well formatted parentheses string if parentheses do not match" in {
    val notWfpStrings =
      Table("notWfps", ")(", "[)")

    forAll(notWfpStrings) { notWfps =>
      validate(notWfps) should be(false)
    }
  }
}
