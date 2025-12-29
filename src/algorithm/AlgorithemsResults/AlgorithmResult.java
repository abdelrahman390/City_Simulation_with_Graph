package algorithm.AlgorithemsResults;

import Model.Edge;
import Model.Node;

import java.util.ArrayList;

public class AlgorithmResult {
    private final ArrayList<Edge> edges;
    private final double totalDistance;
    private final Node StartNode;
    private final Node EndNode;

    public AlgorithmResult(
            ArrayList<Edge> path,
            double totalDistance, Node StartNode, Node EndNode
    ) {
        this.edges = new ArrayList<>(path); // defensive copy
        this.totalDistance = totalDistance;
        this.StartNode = StartNode;
        this.EndNode = EndNode;
    }

    public AlgorithmResult(ArrayList<Edge> edges, double totalDistance) {
        this.edges = edges;
        this.totalDistance = totalDistance;
        StartNode = null;
        EndNode = null;
    }

    public ArrayList<Edge> getEdges() {
        return new ArrayList<>(edges); // immutability protection
    }

    public double getTotalCost() {
        return totalDistance;
    }

    public Node getEndNode() {
        return EndNode;
    }

    public Node getStartNode() {
        return StartNode;
    }
}