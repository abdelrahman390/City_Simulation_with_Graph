package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.sqrt;

public class Graph {
    private ArrayList<String> cityNames;
    private Map<String, Node> allNodes = new HashMap<>();
    private ArrayList<Node> roadNodes = new ArrayList<Node>();
    private ArrayList<Edge> roadEdges =  new ArrayList<>();
    private ArrayList<Node> cities = new ArrayList<>();

    public Graph(ArrayList<String> cityNames, Map<String, Node> allNodes, ArrayList<Node> roadNodes, ArrayList<Edge> roadEdges, ArrayList<Node> cities) {
        this.cityNames = cityNames;
        this.allNodes = allNodes;
        this.roadNodes = roadNodes;
        this.roadEdges = roadEdges;
        this.cities = cities;
    }

    public ArrayList<String> getCityNames() {
        return cityNames;
    }

    public Edge getEdge(String v1, String v2) {
        for (Edge e : roadEdges) {
            if (e.getEdge1().equals(v1) && e.getEdge2().equals(v2)) {
                return e;
            }
            if (e.getEdge1().equals(v2) && e.getEdge2().equals(v1)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Node> getCities() {
        return cities;
    }

    public ArrayList<Node> getRoadNodes() {
        return roadNodes;
    }

    public ArrayList<Edge> getRoadEdges() {
        return roadEdges;
    }

    public Node getNode(String name) {
        return allNodes.get(name);
    }

    public Map<String, Node> getAllNodes() {
        return allNodes;
    }
}
