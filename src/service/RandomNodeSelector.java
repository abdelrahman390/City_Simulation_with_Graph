package service;

import City.City;
import Model.Graph;
import Model.Node;

import java.util.ArrayList;
import java.util.Random;

public class RandomNodeSelector {
    private static final Random random = new Random();

    public static Node getRandomNode(){
        City c = City.getCityInstance();
        Graph graph = c.getGraph();

        ArrayList<Node> cities = graph.getCities();
        int index = random.nextInt(cities.size());

        return cities.get(index);
    }

    public static Node getDeferentRandomNode(Node node) {
        City c = City.getCityInstance();
        Graph graph = c.getGraph();

        ArrayList<Node> cities = graph.getCities();
        int index = random.nextInt(cities.size());
        while(cities.get(index).equals(node)){
            index = random.nextInt(cities.size());
        }

        return cities.get(index);
    }

}
