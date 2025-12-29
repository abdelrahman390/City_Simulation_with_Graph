package Model;

import java.util.ArrayList;
import java.util.Map;

public class Graph {
    private final ArrayList<String> cityNames;
    private final Map<String, Node> allNodes;
    private final ArrayList<Node> roadNodes;
    private final ArrayList<Edge> roadEdges;
    private final ArrayList<Node> cities;

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
