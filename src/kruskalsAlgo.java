import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class kruskals {
    public returnTwoValuesEdgesAndDouble kruskalsMST(ArrayList<Node> cities, ArrayList<Edge> edges) {

        // Sort all edges based on weight
        Collections.sort(edges);

        // Traverse edges in sorted order
        DSU dsu = new DSU(cities);
        int count = 0;
        double cost = 0;

        ArrayList<Edge> finalEdges  = new ArrayList<>();
        for (Edge e : edges) {
            String x = e.edge1, y = e.edge2;
            double w = e.weight;

            // Make sure that there is no cycle
            if (dsu.find(dsu.getNodeStruct(x)) != dsu.find(dsu.getNodeStruct(y))) {
                dsu.union(x, y);
                cost += w;
                finalEdges.add(e);
                if (++count == cities.size() - 1) break;
            }
        }
        return new returnTwoValuesEdgesAndDouble(finalEdges, cost);
    }

}

// Disjoint set data structure
class DSU {
    //    private nodeStruct[] nodesStruc;
    private Map<String, nodeStruct> nodeMap = new HashMap<>();

    public DSU(ArrayList<Node> cities) {
        int n = cities.size();
//        nodesStruc = new nodeStruct[n];
        for (Node city : cities) {
            nodeStruct newNode = new nodeStruct(city.name, null, 0);
            newNode.parent = newNode; // Each node starts as its own parent
            nodeMap.put(city.name, newNode);
        }
    }

    public nodeStruct getNodeStruct(String name) {
        return nodeMap.get(name);
    }

    // Find with path compression
    public nodeStruct find(nodeStruct x) {
        // If x is not the root (its parent is not itself)
        if (x.parent != x) {
            // Recursively find the root and update parent
            x.parent = find(x.parent);
        }
        return x.parent;
    }

    // Union by rank
    public void union(String x, String y) {
        nodeStruct nodeX = nodeMap.get(x);
        nodeStruct nodeY = nodeMap.get(y);

        // Find roots
        nodeStruct rootX = find(nodeX);
        nodeStruct rootY = find(nodeY);

        // If they're already in the same set, do nothing
        if (rootX == rootY) {
            return;
        }

        // Union by rank: attach smaller tree to larger tree
        if (rootX.rank < rootY.rank) {
            rootX.parent = rootY;
        } else if (rootX.rank > rootY.rank) {
            rootY.parent = rootX;
        } else {
            // Ranks are equal, choose one as parent and increment its rank
            rootY.parent = rootX;
            rootX.rank++;
        }
    }
}


class nodeStruct {
    String name;
    nodeStruct parent;
    int rank;

    public nodeStruct(String name, nodeStruct parent, int rank) {
        this.name = name;
        this.parent = parent;
        this.rank = rank;
    }
}
