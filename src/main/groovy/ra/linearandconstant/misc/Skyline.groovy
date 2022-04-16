package ra.linearandconstant.misc

import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

import static ra.linearandconstant.misc.Skyline.Edge.Direction.ASC
import static ra.linearandconstant.misc.Skyline.Edge.Direction.DESC

/**
 * @author Remus Amalinei
 */
class Skyline {

    static List<Integer> computeSkyline(List<Building> buildingList) {
        List<Edge> sortedEdgeList = extractSortedEdgesFromBuildings(buildingList)
        Queue<Integer> highestFirstQueue = new PriorityQueue<>(highestFirstComparator())
        List<Integer> skylineList = []

        sortedEdgeList.each { Edge edge ->
            if (edge.direction == ASC) {
                if (edgeModifiesSkyline(edge, highestFirstQueue)) {
                    skylineList << edge.x
                    skylineList << edge.height
                }

                highestFirstQueue.offer(edge.height)
            } else {
                highestFirstQueue.remove(edge.height)

                if (edgeModifiesSkyline(edge, highestFirstQueue)) {
                    skylineList << edge.x
                    skylineList << highestOrZero(highestFirstQueue)
                }
            }
        }

        skylineList
    }

    static private List<Edge> extractSortedEdgesFromBuildings(List<Building> buildingList) {
        List<Edge> edgeList = []
        buildingList.each { Building building ->
            edgeList.addAll(building.extractEdges())
        }

        edgeList.sort()

        edgeList
    }

    @PackageScope
    static Comparator<Integer> highestFirstComparator() {
        { Integer a, Integer b ->
            b <=> a
        } as Comparator<Integer>
    }

    static private Boolean edgeModifiesSkyline(Edge edge, Queue<Integer> highestFirstQueue) {
        highestOrZero(highestFirstQueue) < edge.height
    }

    static private Integer highestOrZero(Queue<Integer> highestFirstQueue) {
        highestFirstQueue.peek() ?: 0
    }

    static class Building {
        Integer x1
        Integer x2
        Integer height

        List<Edge> extractEdges() {
            [
                    new Edge(direction: ASC, x: x1, height: height),
                    new Edge(direction: DESC, x: x2, height: height)]
        }
    }

    @EqualsAndHashCode
    @ToString
    static class Edge implements Comparable<Edge> {

        static enum Direction {
            ASC,
            DESC
        }

        Direction direction
        Integer x
        Integer height

        @Override
        int compareTo(Edge other) {
            if (x != other.x) {
                x <=> other.x
            } else {
                compareOnTie(other)
            }
        }

        private int compareOnTie(Edge other) {
            if (direction == ASC) {
                if (other.direction == ASC) {
                    other.height <=> height
                } else {
                    -1
                }
            } else {
                if (other.direction == ASC) {
                    1
                } else {
                    height <=> other.height
                }
            }
        }
    }
}
