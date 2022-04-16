package ra.linearandconstant.recursion

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class PermutationsTest extends Specification {

    void 'should generate all permutations'() {
        given:
        Integer n = 5
        Integer nFactorial = 120

        when:
        List<List<Integer>> permutationList = new Permutations().generate(n)

        then:

        containsDistinctListsOfSize(permutationList, nFactorial, n, n)
    }

    // TODO still difficult to read
    static boolean containsDistinctListsOfSize(List<List<Integer>> listOfLists, Integer distinctListCount, Integer listSize, Integer maxValue) {
        List<Integer> allElementsList = []
        1.upto(maxValue) { i ->
            allElementsList << i
        }

        Set<List<Integer>> set = (listOfLists as Set<List<Integer>>)

        if (set.size() != distinctListCount) {
            return false
        }

        for (list in listOfLists) {
            if ((list.size() != listSize) || (!allElementsList.containsAll(list))) {
                return false
            }
        }

        true
    }
}
