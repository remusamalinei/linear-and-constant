package ra.linearandconstant.bitwise

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class MissingNumberTest extends Specification {

    // TODO add tests for invalid input

    void 'should find the missing number'() {
        given:
        Integer min =   999_000_000
        Integer max = 1_000_000_000
        List<Integer> integerList = []
        min.upto(max) {
            integerList << it
        }
        Collections.shuffle(integerList)
        Integer expectedMissingNumber = integerList.remove(0)

        when:
        Integer missingNumber = new MissingNumber().find(integerList, min, max)

        then:
        missingNumber == expectedMissingNumber
    }
}
