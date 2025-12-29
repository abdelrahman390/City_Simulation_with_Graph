package algorithm.MST.kruskals;

import Model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Disjoint set data structure
class DSU {
    //    private nodeStruct[] nodesStruc;
    private Map<String, nodeStruct> nodeMap = new HashMap<>();

    public DSU(ArrayList<Node> cities) {
        int n = cities.size();

        for (Node city : cities) {
            nodeStruct newNode = new nodeStruct(city.getName(), null, 0);
            newNode.parent = newNode; // Each node starts as its own parent
            nodeMap.put(city.getName(), newNode);
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