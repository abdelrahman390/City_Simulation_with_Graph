package graphBuilder;
import Model.Edge;
import Model.Graph;
import Model.Node;
import service.CalcEdgeWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CityGraphBuilder {
    private final ArrayList<String> cityNames = new ArrayList<>();
    private final Map<String, Node> allNodes = new HashMap<>();
    private final ArrayList<Node> roadNodes = new ArrayList<>();
    private final ArrayList<Edge> roadEdges =  new ArrayList<>();
    private final ArrayList<Node> cities = new ArrayList<>();
    private final CalcEdgeWeight calcWeightClass = new CalcEdgeWeight();

    private void createRoadEdge(int x1, int y1, int x2, int y2, String name1Name, String name2Name) {
        double weight = calcWeightClass.calcWeight(allNodes.get(name1Name), allNodes.get(name2Name));
        Edge e = new Edge(x1, y1, x2, y2,
                name1Name, name2Name, weight);
        roadEdges.add(e);
        allNodes.get(name1Name).addOutGoingNodes(allNodes.get(name2Name));
        allNodes.get(name2Name).addOutGoingNodes(allNodes.get(name1Name));
    }

    private void addNode(String name, int x, int y, boolean isCity){
        Node n =  new Node(name, x, y);
        if(isCity){
            cities.add(n);
            cityNames.add(name);
        } else {
            roadNodes.add(n);
        }
        allNodes.put(name, n);
    }

    public Graph build() {
        addNode("A", 50, 60, true);
        addNode("B", 20, 260, true);
        addNode("C", 300, 390, true);
        addNode("D", 650, 413, true);
        addNode("E", 250, 40, true);
        addNode("F", 520, 280, true);

        // random nodes
        addNode("R1", 120, 180, false);    // Random position
        addNode("R2", 280, 220, false);    // Random position
        addNode("R3", 450, 150, false);    // Random position
        addNode("R4", 380, 380, false);    // Random position
        addNode("R5", 180, 480, false);    // Random position
        addNode("R6", 550, 250, false);    // Random position
        addNode("R7", 90, 320, false);     // Random position
        addNode("R8", 320, 100, false);    // Random position
        addNode("R9", 480, 420, false);    // Random position
        addNode("R10", 600, 180, false);   // Random position
        addNode("R11", 150, 240, false);   // Random position
        addNode("R12", 420, 300, false);   // Random position

        // City to Road connections
        createRoadEdge(cities.get(0).getX(), cities.get(0).getY(), roadNodes.get(0).getX(), roadNodes.get(0).getY(), "A", "R1");
        createRoadEdge(cities.get(0).getX(), cities.get(0).getY(), roadNodes.get(6).getX(), roadNodes.get(6).getY(), "A", "R7");

        createRoadEdge(cities.get(1).getX(), cities.get(1).getY(), roadNodes.get(4).getX(), roadNodes.get(4).getY(), "B", "R5");
        createRoadEdge(cities.get(1).getX(), cities.get(1).getY(), roadNodes.get(10).getX(), roadNodes.get(10).getY(), "B", "R11");

        createRoadEdge(cities.get(2).getX(), cities.get(2).getY(), roadNodes.get(3).getX(), roadNodes.get(3).getY(), "C", "R4");
        createRoadEdge(cities.get(2).getX(), cities.get(2).getY(), roadNodes.get(8).getX(), roadNodes.get(8).getY(), "C", "R9");
        createRoadEdge(cities.get(2).getX(), cities.get(2).getY(), roadNodes.get(11).getX(), roadNodes.get(11).getY(), "C", "R12");
        createRoadEdge(cities.get(2).getX(), cities.get(2).getY(), roadNodes.get(4).getX(), roadNodes.get(4).getY(), "C", "R5");

        createRoadEdge(cities.get(3).getX(), cities.get(3).getY(), roadNodes.get(5).getX(), roadNodes.get(5).getY(), "D", "R6");
        createRoadEdge(cities.get(3).getX(), cities.get(3).getY(), roadNodes.get(9).getX(), roadNodes.get(9).getY(), "D", "R10");

        createRoadEdge(cities.get(4).getX(), cities.get(4).getY(), roadNodes.get(1).getX(), roadNodes.get(1).getY(), "E", "R2");
        createRoadEdge(cities.get(4).getX(), cities.get(4).getY(), roadNodes.get(7).getX(), roadNodes.get(7).getY(), "E", "R8");

        createRoadEdge(cities.get(5).getX(), cities.get(5).getY(), roadNodes.get(2).getX(), roadNodes.get(2).getY(), "F", "R3");
        createRoadEdge(cities.get(5).getX(), cities.get(5).getY(), roadNodes.get(5).getX(), roadNodes.get(5).getY(), "F", "R6");
        createRoadEdge(cities.get(5).getX(), cities.get(5).getY(), roadNodes.get(11).getX(), roadNodes.get(11).getY(), "F", "R12");

        // Road node to Road node connections (creating a network)
        createRoadEdge(roadNodes.get(0).getX(), roadNodes.get(0).getY(), roadNodes.get(1).getX(), roadNodes.get(1).getY(), "R1", "R2");
        createRoadEdge(roadNodes.get(0).getX(), roadNodes.get(0).getY(), roadNodes.get(10).getX(), roadNodes.get(10).getY(), "R1", "R11");

        createRoadEdge(roadNodes.get(1).getX(), roadNodes.get(1).getY(), roadNodes.get(7).getX(), roadNodes.get(7).getY(), "R2", "R8");
        createRoadEdge(roadNodes.get(1).getX(), roadNodes.get(1).getY(), roadNodes.get(2).getX(), roadNodes.get(2).getY(), "R2", "R3");

        createRoadEdge(roadNodes.get(2).getX(), roadNodes.get(2).getY(), roadNodes.get(11).getX(), roadNodes.get(11).getY(), "R3", "R12");
        createRoadEdge(roadNodes.get(2).getX(), roadNodes.get(2).getY(), roadNodes.get(9).getX(), roadNodes.get(9).getY(), "R3", "R10");
        createRoadEdge(roadNodes.get(3).getX(), roadNodes.get(3).getY(), roadNodes.get(8).getX(), roadNodes.get(8).getY(), "R4", "R9");
        createRoadEdge(roadNodes.get(4).getX(), roadNodes.get(4).getY(), roadNodes.get(6).getX(), roadNodes.get(6).getY(), "R5", "R7");
        createRoadEdge(roadNodes.get(4).getX(), roadNodes.get(4).getY(), roadNodes.get(10).getX(), roadNodes.get(10).getY(), "R5", "R11");

        createRoadEdge(roadNodes.get(5).getX(), roadNodes.get(5).getY(), roadNodes.get(9).getX(), roadNodes.get(9).getY(), "R6", "R10");
        createRoadEdge(roadNodes.get(6).getX(), roadNodes.get(6).getY(), roadNodes.get(10).getX(), roadNodes.get(10).getY(), "R7", "R11");
        createRoadEdge(roadNodes.get(8).getX(), roadNodes.get(8).getY(), roadNodes.get(11).getX(), roadNodes.get(11).getY(), "R9", "R12");
        createRoadEdge(roadNodes.get(11).getX(), roadNodes.get(11).getY(), roadNodes.get(3).getX(), roadNodes.get(3).getY(), "R12", "R4");

        return new Graph(cityNames, allNodes, roadNodes, roadEdges, cities);
    }

}
