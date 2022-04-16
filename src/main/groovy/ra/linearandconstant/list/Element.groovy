package ra.linearandconstant.list

import javax.annotation.Generated

/**
 * @author Remus Amalinei
 */
class Element<T> {

    T value
    Element next

    @Override
    @Generated('by IntelliJ')
    boolean equals(Object obj) {
        if (this.is(obj)) return true
        if (getClass() != obj.class) return false

        Element element = (Element) obj

        if (value != element.value) return false
        if (next) {
            return next == element.next
        }

        return true
    }

    @Override
    int hashCode() {
        throw new UnsupportedOperationException('not implemented')
    }
}
