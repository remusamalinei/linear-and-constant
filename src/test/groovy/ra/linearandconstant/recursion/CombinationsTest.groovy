package ra.linearandconstant.recursion

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class CombinationsTest extends Specification {

    void 'should generate all combinations'() {
        given:
        Integer n = 10
        Integer k = 3
        Integer expectedCombinationCount = 120 // n! / (k! * (n - k)!)

        when:
        List<List<Integer>> combinationList = new Combinations().generate(n, k)

        then:
        PermutationsTest.containsDistinctListsOfSize(combinationList, expectedCombinationCount, k, n)
    }
}
