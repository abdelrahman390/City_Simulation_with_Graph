package algorithm.shortestpath.dijkstra;

import Model.Node;

class DijkstraNode implements Comparable<DijkstraNode>{
    Node node;
    Node prev;
    double cost;

    public DijkstraNode(Node node, double cost) {
        this.node = node;
        this.cost = cost;
        prev = node;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DijkstraNode)) return false;
        DijkstraNode that = (DijkstraNode) o;
        return node.getName().equals(that.node.getName());
    }

    @Override
    public int hashCode() {
        return node.getName().hashCode();
    }

    @Override
    public String toString() {
        return node.getName() + " ( cost: " + cost + ")" + " , Prev: " + prev;
    }

    @Override
    public int compareTo(DijkstraNode o) {
        return Double.compare(cost, o.cost);
    }

}