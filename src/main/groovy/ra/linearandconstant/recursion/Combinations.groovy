package ra.linearandconstant.recursion

/**
 * @author Remus Amalinei
 */
class Combinations {

    List<List<Integer>> generate(Integer n, Integer k) {
        List<List<Integer>> combinationList = []
        List<Integer> currentCombination = []
        1.upto(k) { i ->
            currentCombination << 0
        }

        combine(combinationList, n, k, 0, currentCombination, 0)

        combinationList
    }

    private void combine(List<List<Integer>> combinationList, Integer n, Integer k, Integer currentIndex,
                         List<Integer> currentCombination, Integer currentCombinationIndex) {

        if (currentCombinationIndex == k) {
            combinationList << new ArrayList<>(currentCombination)
            return
        }

        for (int i = currentIndex; i < n; i++) {
            currentCombination[currentCombinationIndex] = (i + 1);
            combine(combinationList, n, k, i + 1, currentCombination, currentCombinationIndex + 1);
        }
    }
}
