package ra.linearandconstant.recursion

/**
 * @author Remus Amalinei
 */
class TowerOfHanoi {

    static void move(Tower source, Tower middle, Tower destination) {
        moveUsingMiddleTower(source.size(), source, middle, destination)
    }

    private static moveUsingMiddleTower(Integer n, Tower source, Tower middle, Tower destination) {
        if (n > 0) {
            moveUsingMiddleTower(n - 1, source, destination, middle)
            destination.push(source.pop())
            moveUsingMiddleTower(n - 1, middle, source, destination)
        }
    }

    static class Tower {
        private Deque<Integer> deque = new LinkedList<>()

        void push(Integer element) {
            if (deque && (deque.peekFirst() <= element)) {
                throw new IllegalArgumentException('the tower accepts elements in descending order')
            }

            deque.offerFirst(element)
        }

        Integer pop() {
            deque.pollFirst()
        }

        Boolean empty() {
            deque.empty
        }

        Integer size() {
            deque.size()
        }
    }
}
