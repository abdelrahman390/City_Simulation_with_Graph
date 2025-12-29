package algorithm.MST.kruskals;

import Model.Edge;
import Model.Graph;
import Model.Node;
import algorithm.AlgorithemsResults.AlgorithmResult;

import java.util.ArrayList;
import java.util.Collections;

class KruskalsEngine {

    public AlgorithmResult performAlgorithm(Graph g) {
        ArrayList<Node> allNodes = new ArrayList<>(g.getAllNodes().values());
        ArrayList<Edge> edges = RemoveRandomEdges.removeRandomEdges(g.getRoadEdges());

        // Sort all edges based on weight
        Collections.sort(edges);

        // Traverse edges in sorted order
        DSU dsu = new DSU(allNodes);
        int count = 0;
        double cost = 0;

        ArrayList<Edge> finalEdges  = new ArrayList<>();
        for (Edge e : edges) {
            String x = e.getEdge1(), y = e.getEdge2();
            double w = e.getWeight();

            // Make sure that there is no cycle
            if (dsu.find(dsu.getNodeStruct(x)) != dsu.find(dsu.getNodeStruct(y))) {
                dsu.union(x, y);
                cost += w;
                finalEdges.add(e);
                if (++count == allNodes.size() - 1) break;
            }
        }

        return new AlgorithmResult(finalEdges, cost);
    }
}
