package ra.linearandconstant.recursion

import static java.util.Collections.swap

/**
 * @author Remus Amalinei
 */
class Permutations {

    List<List<Integer>> generate(Integer n) {
        List<List<Integer>> permutationList = []
        List<Integer> currentPermutation = []
        1.upto(n) { i ->
            currentPermutation << i
        }

        permute(permutationList, currentPermutation, n - 1)
        
        permutationList
    }

    private void permute(List<List<Integer>> permutationList, List<Integer> currentPermutation, Integer currentIndex) {
        if (currentIndex < 0) {
            permutationList << new ArrayList<Integer>(currentPermutation)
        }

        for (Integer i = currentIndex; i >= 0; i--) {
            swap(currentPermutation, currentIndex, i)
            permute(permutationList, currentPermutation, currentIndex - 1)
            swap(currentPermutation, currentIndex, i)
        }
    }
}
