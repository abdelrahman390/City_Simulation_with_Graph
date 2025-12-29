package algorithm.MST.kruskals;

import City.City;
import Model.Edge;
import Model.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveRandomEdges {

    public static ArrayList<Edge> removeRandomEdges(ArrayList<Edge> edges) {
        ArrayList<Edge> edgesCopy = new ArrayList<>(edges);
        City c =  City.getCityInstance();
        Graph g =  c.getGraph();
        Set<String> citySet = new HashSet<>(g.getCityNames());
        int removedEdges = 0;
        while(removedEdges < 3){
            int rendomIndex = (int) (Math.random() * edgesCopy.size());
            if(!citySet.contains(edgesCopy.get(rendomIndex).getEdge1()) && !citySet.contains(edgesCopy.get(rendomIndex).getEdge2())){
                edgesCopy.remove(rendomIndex);
                removedEdges++;
            }
        }

        return edgesCopy;
    }
}
