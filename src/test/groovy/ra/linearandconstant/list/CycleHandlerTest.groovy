package ra.linearandconstant.list

import spock.lang.Specification

import static ra.linearandconstant.list.CycleHandler.breakCycle
import static ra.linearandconstant.list.CycleHandler.calculateCycleLength

/**
 * @author Remus Amalinei
 */
class CycleHandlerTest extends Specification {

    void 'should calculate the length of the cycle'() {
        given:
        Element<Integer> listWithACycleOfLength3 = listWithACycleOfLength3()

        when:
        Integer cycleLength = calculateCycleLength(listWithACycleOfLength3)

        then:
        cycleLength == 3
    }

    void 'should calculate the length of the cycle when is of length 1 starting at head'() {
        given:
        Element<Integer> listWithACycleOfLength1StartingAtHead = new Element<>(value: 1)
        listWithACycleOfLength1StartingAtHead.next = listWithACycleOfLength1StartingAtHead

        when:
        Integer cycleLength = calculateCycleLength(listWithACycleOfLength1StartingAtHead)

        then:
        cycleLength == 1
    }

    void 'should return 0 for a list with no cycles'() {
        expect:
        calculateCycleLength(listWithNoCycle()) == 0
    }

    void 'should throw an IllegalArgumentException when the list to calculate cycle length for is null'() {
        when:
        calculateCycleLength(null)

        then:
        thrown(IllegalArgumentException)
    }

    void 'should break cycle'() {
        expect:
        listWithNoCycle() == breakCycle(listWithACycleOfLength3())
    }

    void 'should not modify list if cycle does not exist'() {
        expect:
        listWithNoCycle() == breakCycle(listWithNoCycle())
    }

    private Element<Integer> listWithNoCycle() {
        new Element(
                value: 1,
                next: new Element<Integer>(
                        value: 2,
                        next: new Element(
                                value: 3,
                                next: new Element(
                                        value: 4))))
    }

    private Element<Integer> listWithACycleOfLength3() {
        // 1 -> 2 -> 3 -> 4
        //      \ <-<-<- /

        Element<Integer> head = new Element(value: 1)
        Element<Integer> element2 = new Element(value: 2)
        Element<Integer> element3 = new Element(value: 3)
        Element<Integer> element4 = new Element(value: 4)

        head.next = element2
        element2.next = element3
        element3.next = element4
        element4.next = element2

        head
    }
}
