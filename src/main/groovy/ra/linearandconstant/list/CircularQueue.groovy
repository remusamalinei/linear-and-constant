package ra.linearandconstant.list

/**
 * @author Remus Amalinei
 */
class CircularQueue<E> {

    Object[] circularBuffer
    Integer head = 0
    Integer tail = 0

    CircularQueue(Integer capacity) {
        circularBuffer = new Object[capacity + 1]
    }

    Boolean offer(E e) {
        if (isFull()) {
            return false
        }

        circularBuffer[head] = e
        head = (head + 1) % circularBuffer.length

        true
    }

    private Boolean isFull() {
        (Math.abs(head - tail) == (circularBuffer.length - 1)) ||
                (head - tail == -1)
    }

    E poll() {
        if (isEmpty()) {
            return null
        }

        E result = circularBuffer[tail] as E
        tail = (tail + 1) % circularBuffer.length

        result
    }

    private Boolean isEmpty() {
        head == tail
    }
}
