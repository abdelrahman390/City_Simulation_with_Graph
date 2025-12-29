package service;

import City.City;
import Model.Edge;
import Model.Graph;

import java.util.Random;

public class RandomizeEdgesDirections {
    private static final Random random = new Random();

    public void makeRandomEdgesDirections(){
        City c = City.getCityInstance();
        Graph graph = c.getGraph();

        UndirectedEdges undirectedEdges = new UndirectedEdges();
        undirectedEdges.makeAllEdgesUndirected();

        for (Edge edge : graph.getRoadEdges()) {
            if(!edge.isDirected()){
                boolean randomInt = random.nextBoolean();
                if(randomInt){
                    // Out Going from node 2 to node 1
                    edge.setToV(edge.getEdge1());
                    edge.setFromV(edge.getEdge2());
                    edge.setDirected(true);
                    graph.getNode(edge.getEdge1()).deleteOutGoingNode((graph.getNode(edge.getEdge2())));
                } else {
                    // Out Going from node 1 to node 2
                    edge.setToV(edge.getEdge2());
                    edge.setFromV(edge.getEdge1());
                    edge.setDirected(true);
                    graph.getNode(edge.getEdge2()).deleteOutGoingNode((graph.getNode(edge.getEdge1())));
                }
            }
        }
    }

}