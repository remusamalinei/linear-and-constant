package ra.linearandconstant.recursion

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class TowerOfHanoiTest extends Specification {

    TowerOfHanoi.Tower stack = new TowerOfHanoi.Tower()

    void 'disks in descending order should be pop-ped in the reserve order as they were push-ed'() {
        when:
        stack.push(3)
        stack.push(2)
        stack.push(1)

        then:
        1 == stack.pop()
        2 == stack.pop()
        3 == stack.pop()
    }

    void 'disks that are not in ascending order should be rejected by the tower'() {
        when:
        stack.push(1)
        stack.push(2)

        then:
        thrown IllegalArgumentException
    }

    void 'should move all disks from the source tower to the destination tower'() {
        given:
        Integer n = 10
        TowerOfHanoi.Tower source = new TowerOfHanoi.Tower()
        n.downto(1) {
            source.push(it)
        }

        TowerOfHanoi.Tower middle = new TowerOfHanoi.Tower()
        TowerOfHanoi.Tower destination = new TowerOfHanoi.Tower()

        when:
        TowerOfHanoi.move(source, middle, destination)

        then:
        source.empty()
        middle.empty()
        1.upto(n) {
            it == destination.pop()
        }
    }
}
