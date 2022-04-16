package ra.linearandconstant.list

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class ReverseListTest extends Specification {

    static final Element HEAD = new Element(
            value: 1,
            next: new Element(
                    value: 2,
                    next: new Element(
                            value: 3,
                            next: new Element(
                                    value: 4))))

    static final HEAD_REVERSED = new Element(
            value: 4,
            next: new Element(
                    value: 3,
                    next: new Element(
                            value: 2,
                            next: new Element(
                                    value: 1))))

    void 'should reverse list recursively'() {
        expect:
        new ReverseList().reverseRecursively(list) == listReversed

        where:
        list                  | listReversed
        null                  | null
        new Element(value: 1) | new Element(value: 1)
        HEAD                  | HEAD_REVERSED
    }
}
