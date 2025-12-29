package algorithm.shortestpath.floydWarshall;

import City.City;
import Model.Edge;
import Model.Graph;
import Model.Node;
import algorithm.AlgorithemsResults.AlgorithmResult;

import java.util.ArrayList;
import java.util.Map;

class cachedResult {
    private final Map<String, Integer> NodesIndex;
    private final double[][] distMatrices;
    private final String[][] prevNodes;

    public cachedResult(Map<String, Integer> nodesIndex, double[][] distMatrices, String[][] prevNodes) {
        NodesIndex = nodesIndex;
        this.distMatrices = distMatrices;
        this.prevNodes = prevNodes;
    }

    public AlgorithmResult getPath(Node startNode, Node endNode) {
        System.out.println("Start: " +  startNode + " End: " +  endNode);
        ArrayList<String> path = new ArrayList<>();
        String current = endNode.getName();

        Integer startIdx = NodesIndex.get(startNode.getName());
        Integer endIdx = NodesIndex.get(endNode.getName());

        // Reconstruct path
        while (current != null) {
            path.add(current);
            Integer currentIdx = NodesIndex.get(current);
            current = prevNodes[startIdx][currentIdx];
        }

//        path.add(startNode.getName());

//        Collections.reverse(path);
        ArrayList<Edge> resArray = new ArrayList<>();

        City c = City.getCityInstance();
        Graph g = c.getGraph();

        for (int i = path.size()-1; i > 0; i--) {
            Edge e = g.getEdge(path.get(i), path.get(i-1));
            if(e != null){
                resArray.add(e);
            } else {
//                System.out.println("Error: " + e);
                return null;
            }
        }

        // Print the path in reversed order
//        System.out.println(String.join(" -> ", path));
        return new AlgorithmResult(resArray, distMatrices[startIdx][endIdx], startNode, endNode);
    }
}
