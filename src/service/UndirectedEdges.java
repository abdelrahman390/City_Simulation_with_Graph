package service;

import City.City;
import Model.Edge;
import Model.Graph;
import Model.Node;

import java.util.ArrayList;

public class UndirectedEdges {

    public void makeAllEdgesUndirected() {
        City c = City.getCityInstance();
        Graph graph = c.getGraph();

        ArrayList<Edge> roadEdges = graph.getRoadEdges();
        for (Edge edge : roadEdges) {
            edge.setDirected(false);
            edge.setToV(null);
            edge.setFromV(null);

            for (Node n : graph.getNode(edge.getEdge1()).getOutGoingNodes()){
                n.addOutGoingNodes(graph.getNode(edge.getEdge1()));
            }

            for (Node n : graph.getNode(edge.getEdge2()).getOutGoingNodes()){
                n.addOutGoingNodes(graph.getNode(edge.getEdge2()));
            }
        }
    }

}
