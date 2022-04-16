package ra.linearandconstant.bitwise

/**
 * A list of numbers contains integers in the range 1 to 1,000,000,000. All numbers appear once except one which is
 * missing. Find the missing number.
 *
 * @author Remus Amalinei
 */
class MissingNumber {

    Integer find(List<Integer> integerList, Integer min, Integer max) {
        Integer missing = max
        Integer listIndex = 0

        for (int i = min; i < max; i++) {
            missing ^= i
            missing ^= integerList[listIndex]
            listIndex++
        }

        missing
    }
}
