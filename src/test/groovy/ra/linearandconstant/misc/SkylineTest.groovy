package ra.linearandconstant.misc

import spock.lang.Specification

import static ra.linearandconstant.misc.Skyline.Building
import static ra.linearandconstant.misc.Skyline.Edge
import static ra.linearandconstant.misc.Skyline.Edge.Direction.ASC
import static ra.linearandconstant.misc.Skyline.Edge.Direction.DESC

/**
 * @author Remus Amalinei
 */
class SkylineTest extends Specification {

    void 'should compute the skyline'() {
        expect:
        Skyline.computeSkyline(buildingList) == [1, 2, 2, 4, 4, 3, 5, 2, 7, 0, 8, 2, 9, 0]

        where:
        buildingList = [
                new Building(x1: 1, x2: 7, height: 2),
                new Building(x1: 2, x2: 4, height: 4),
                new Building(x1: 2, x2: 7, height: 1),
                new Building(x1: 3, x2: 5, height: 3),
                new Building(x1: 4, x2: 6, height: 2),
                new Building(x1: 8, x2: 9, height: 2),
                new Building(x1: 8, x2: 9, height: 2)]
    }

    void 'should extract edges from building'() {
        expect:
        building.extractEdges() == [
                new Edge(direction: ASC, x: 1, height: 3),
                new Edge(direction: DESC, x: 2, height: 3)]

        where:
        building = new Building(x1: 1, x2: 2, height: 3)
    }

    void 'natural order should sort edges by x'() {
        given:
        List<Edge> edgeList = [
                new Edge(direction: DESC, x: 1, height: 1),
                new Edge(direction: ASC, x: 1, height: 1),
                new Edge(direction: ASC, x: 5, height: 2),
                new Edge(direction: ASC, x: 5, height: 3),
                new Edge(direction: DESC, x: 5, height: 2),
                new Edge(direction: DESC, x: 5, height: 1)]

        List<Edge> expectedSortedEdgeList = [
                new Edge(direction: ASC, x: 1, height: 1),
                new Edge(direction: DESC, x: 1, height: 1),
                new Edge(direction: ASC, x: 5, height: 3),
                new Edge(direction: ASC, x: 5, height: 2),
                new Edge(direction: DESC, x: 5, height: 1),
                new Edge(direction: DESC, x: 5, height: 2)]

        when:
        edgeList.sort()

        then: '''on tie ASC is before DESC
                if both directions are ASC, highest first
                if both directions are DESC, highest last'''
        edgeList == expectedSortedEdgeList
    }

    void 'highest first comparator should sort edges by height'() {
        given:
        List<Integer> heightList = [1, 3, 2]

        List<Integer> expectedSortedHeightList = [3, 2, 1]

        when:
        heightList.sort(Skyline.highestFirstComparator())

        then:
        heightList == expectedSortedHeightList
    }
}
