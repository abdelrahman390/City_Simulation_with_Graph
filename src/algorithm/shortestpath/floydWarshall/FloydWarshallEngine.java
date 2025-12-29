package algorithm.shortestpath.floydWarshall;

import City.City;
import Model.Edge;
import Model.Graph;
import Model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class FloydWarshallEngine {
     Map<String, Integer> NodesIndex = new HashMap<>();
    double[][] distMatrices;
    String[][] prevNodes;

    public cachedResult performAlgorithm(ArrayList<Node> nodes){
        int n = nodes.size();
        distMatrices = new double[n][n];
        prevNodes = new String[n][n];

        City c = City.getCityInstance();
        Graph g = c.getGraph();

        for (int i = 0; i < n; i++) {
            NodesIndex.put(nodes.get(i).getName(), i);
        }

        // initializing
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j){
                    distMatrices[i][j] = 0;
                } else {
                    Edge e = g.getEdge(nodes.get(i).getName(), nodes.get(j).getName());
                    if(e != null){
                        if(e.isDirected() && e.getFromV().equals(nodes.get(i).getName()) &&  e.getToV().equals(nodes.get(j).getName())){
                            distMatrices[i][j] = e.getWeight();
                            prevNodes[i][j] = nodes.get(i).getName(); // ✅ predecessor
                        } else if(!e.isDirected()) {
                            distMatrices[i][j] = e.getWeight();
                            prevNodes[i][j] = nodes.get(i).getName(); // ✅ predecessor
                        } else {
                            distMatrices[i][j] = Double.POSITIVE_INFINITY;
                        }
                    } else {
                        distMatrices[i][j] = Double.POSITIVE_INFINITY;
                    }
                }
            }
        }

        // Main Floyd–Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distMatrices[i][k] + distMatrices[k][j] < distMatrices[i][j]) {
                        distMatrices[i][j] = distMatrices[i][k] + distMatrices[k][j];
                        prevNodes[i][j] = prevNodes[k][j];
                    }
                }
            }
        }

        return new cachedResult(NodesIndex, distMatrices,  prevNodes);
    }

}
