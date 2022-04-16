package ra.linearandconstant.list

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class CircularQueueTest extends Specification {

    CircularQueue<String> circularQueue = new CircularQueue<>(5)

    void 'offer and poll should have a FIFO behavior'() {
        when:
        circularQueue.offer('A')
        circularQueue.offer('B')
        circularQueue.offer('C')
        circularQueue.offer('D')
        circularQueue.offer('E')
        circularQueue.poll()
        circularQueue.poll()
        circularQueue.poll()
        circularQueue.offer('F')
        circularQueue.offer('G')

        then:
        'D' == circularQueue.poll()
        'E' == circularQueue.poll()
        'F' == circularQueue.poll()
        'G' == circularQueue.poll()
    }

    void 'should work '() {
        when:
        circularQueue.offer('A')
        circularQueue.offer('B')
        circularQueue.offer('C')

        then:
        'A' == circularQueue.poll()
        'B' == circularQueue.poll()
        'C' == circularQueue.poll()
    }

    void 'poll called on an empty queue should return null'() {
        expect:
        !circularQueue.poll()
    }

    void 'offer should not add more than capacity'() {
        when:
        Boolean resultA = circularQueue.offer('A')
        Boolean resultB = circularQueue.offer('B')
        Boolean resultC = circularQueue.offer('C')
        Boolean resultD = circularQueue.offer('D')
        Boolean resultE = circularQueue.offer('E')
        Boolean resultF = circularQueue.offer('F')

        then:
        resultA
        resultB
        resultC
        resultD
        resultE
        !resultF
    }
}
