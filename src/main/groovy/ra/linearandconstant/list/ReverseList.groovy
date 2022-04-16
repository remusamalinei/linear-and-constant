package ra.linearandconstant.list

/**
 * @author Remus Amalinei
 */
class ReverseList {

    Element reverseRecursively(Element head) {
        if (!head || !head.next) {
            return head
        }

        Element secondElement = head.next
        head.next = null

        Element secondElementReversed = reverseRecursively(secondElement)
        secondElement.next = head

        secondElementReversed
    }
}
