import java.util.*;
import static java.lang.Math.*;

public class City {
    String[] cityNames = {"A", "B", "C", "D", "E", "F"};
    Map<String, Node> allNodes = new HashMap<>();
    private ArrayList<Node> cities = new ArrayList<>();
    private ArrayList<Node> roadNodes = new ArrayList<Node>();
    ArrayList<Edge> roadEdges =  new ArrayList<>();
    private static City cityInstance;

    private Random random = new Random();

    public static City getCityInstance() {
        if(cityInstance == null){
            cityInstance = new City();
        }
        return cityInstance;
    }

    private City() {
        Random r= new Random();
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
        createRoadEdge(cities.get(0).x, cities.get(0).y, roadNodes.get(0).x, roadNodes.get(0).y, "A", "R1");
        createRoadEdge(cities.get(0).x, cities.get(0).y, roadNodes.get(6).x, roadNodes.get(6).y, "A", "R7");

        createRoadEdge(cities.get(1).x, cities.get(1).y, roadNodes.get(4).x, roadNodes.get(4).y, "B", "R5");
        createRoadEdge(cities.get(1).x, cities.get(1).y, roadNodes.get(10).x, roadNodes.get(10).y, "B", "R11");

        createRoadEdge(cities.get(2).x, cities.get(2).y, roadNodes.get(3).x, roadNodes.get(3).y, "C", "R4");
        createRoadEdge(cities.get(2).x, cities.get(2).y, roadNodes.get(8).x, roadNodes.get(8).y, "C", "R9");
        createRoadEdge(cities.get(2).x, cities.get(2).y, roadNodes.get(11).x, roadNodes.get(11).y, "C", "R12");
        createRoadEdge(cities.get(2).x, cities.get(2).y, roadNodes.get(4).x, roadNodes.get(4).y, "C", "R5");

        createRoadEdge(cities.get(3).x, cities.get(3).y, roadNodes.get(5).x, roadNodes.get(5).y, "D", "R6");
        createRoadEdge(cities.get(3).x, cities.get(3).y, roadNodes.get(9).x, roadNodes.get(9).y, "D", "R10");

        createRoadEdge(cities.get(4).x, cities.get(4).y, roadNodes.get(1).x, roadNodes.get(1).y, "E", "R2");
        createRoadEdge(cities.get(4).x, cities.get(4).y, roadNodes.get(7).x, roadNodes.get(7).y, "E", "R8");

        createRoadEdge(cities.get(5).x, cities.get(5).y, roadNodes.get(2).x, roadNodes.get(2).y, "F", "R3");
        createRoadEdge(cities.get(5).x, cities.get(5).y, roadNodes.get(5).x, roadNodes.get(5).y, "F", "R6");
        createRoadEdge(cities.get(5).x, cities.get(5).y, roadNodes.get(11).x, roadNodes.get(11).y, "F", "R12");

        // Road node to Road node connections (creating a network)
        createRoadEdge(roadNodes.get(0).x, roadNodes.get(0).y, roadNodes.get(1).x, roadNodes.get(1).y, "R1", "R2");
        createRoadEdge(roadNodes.get(0).x, roadNodes.get(0).y, roadNodes.get(10).x, roadNodes.get(10).y, "R1", "R11");

        createRoadEdge(roadNodes.get(1).x, roadNodes.get(1).y, roadNodes.get(7).x, roadNodes.get(7).y, "R2", "R8");
        createRoadEdge(roadNodes.get(1).x, roadNodes.get(1).y, roadNodes.get(2).x, roadNodes.get(2).y, "R2", "R3");

        createRoadEdge(roadNodes.get(2).x, roadNodes.get(2).y, roadNodes.get(11).x, roadNodes.get(11).y, "R3", "R12");
        createRoadEdge(roadNodes.get(2).x, roadNodes.get(2).y, roadNodes.get(9).x, roadNodes.get(9).y, "R3", "R10");
        createRoadEdge(roadNodes.get(3).x, roadNodes.get(3).y, roadNodes.get(8).x, roadNodes.get(8).y, "R4", "R9");
        createRoadEdge(roadNodes.get(4).x, roadNodes.get(4).y, roadNodes.get(6).x, roadNodes.get(6).y, "R5", "R7");
        createRoadEdge(roadNodes.get(4).x, roadNodes.get(4).y, roadNodes.get(10).x, roadNodes.get(10).y, "R5", "R11");

        createRoadEdge(roadNodes.get(5).x, roadNodes.get(5).y, roadNodes.get(9).x, roadNodes.get(9).y, "R6", "R10");

        createRoadEdge(roadNodes.get(6).x, roadNodes.get(6).y, roadNodes.get(10).x, roadNodes.get(10).y, "R7", "R11");

        createRoadEdge(roadNodes.get(8).x, roadNodes.get(8).y, roadNodes.get(11).x, roadNodes.get(11).y, "R9", "R12");

        createRoadEdge(roadNodes.get(11).x, roadNodes.get(11).y, roadNodes.get(3).x, roadNodes.get(3).y, "R12", "R4");

//        System.out.println("roadEdges: " + roadEdges);
    }

    public void createRoadEdge(int x1, int y1, int x2, int y2, String name1Name, String name2Name) {
        double distance = getDistance(x1, y1, x2, y2);
        Edge e = new Edge(x1, y1, x2, y2,
                name1Name, name2Name, distance);
        roadEdges.add(e);
        allNodes.get(name1Name).addOutGoingNodes(allNodes.get(name2Name));
        allNodes.get(name2Name).addOutGoingNodes(allNodes.get(name1Name));
    }

    public void addNode(String name, int x, int y, boolean isCity){
        Node n =  new Node(name, x, y);
        if(isCity){
            cities.add(n);
        } else {
            roadNodes.add(n);
        }
        allNodes.put(name, n);
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        return sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // return ArrayList<Edge>
    public returnTwoValuesEdgesAndDouble GetShortestDistanceBetweenTwoNodesRandomlyDijkstr(){
        int ramdomStartNode = (int) ((Math.random() * (cities.size())));
        int ramdomEndNode = (int) ((Math.random() * (cities.size())));
        while(ramdomStartNode == ramdomEndNode){
            ramdomStartNode = (int) ((Math.random() * (cities.size())));
            ramdomEndNode = (int) ((Math.random() * (cities.size())));
        }
        ArrayList<Node> valueList = new ArrayList<Node>(allNodes.values());
        returnTwoValuesEdgesAndDouble theFinalRoad = DijkstraAlgo.getShortestPath(valueList ,cities.get(ramdomStartNode),  cities.get(ramdomEndNode));

        return new returnTwoValuesEdgesAndDouble(theFinalRoad.arrayList, theFinalRoad.doubleValue);
    }

    public returnTwoValuesEdgesAndDouble buildPipLinesWithKruskalsAlgo() {
        ArrayList<Edge> edges = new ArrayList<>(roadEdges);

        Set<String> citySet = new HashSet<>(Arrays.asList(cityNames));
        int removedEdges = 0;
        while(removedEdges < 3){
            int rendomIndex = (int) (Math.random() * edges.size());
            if(!citySet.contains(edges.get(rendomIndex).edge1) && !citySet.contains(edges.get(rendomIndex).edge2)){
                edges.remove(rendomIndex);
                removedEdges++;
            }
        }

        kruskals kruskalsAlgo = new kruskals();
        if(!edges.isEmpty()){
            return kruskalsAlgo.kruskalsMST(new ArrayList<>(allNodes.values()), edges);
        }
        return null;
    }

    public returnTwoValuesEdgesAndDouble GetShortestPathFloydWarshall(){
        FloydWarshall f = FloydWarshall.getInstance();
//        f.getRandomShortestRoadFloydWarshall(new ArrayList<>(allNodes.values()));
        return f.getRandomShortestRoadFloydWarshall(new ArrayList<>(allNodes.values()));
    }

    public void deleteAlgorithmsInstances() {
        FloydWarshall f = FloydWarshall.getInstance();
        f.deleteInstance();
    }

    public void makeEdgesUndirected() {
        for (Edge edge : roadEdges) {
            edge.isDirected = false;
            edge.toV = null;
            edge.fromV = null;
            for (Node n : allNodes.get(edge.edge1).outGoingNodes){
                 n.addOutGoingNodes(allNodes.get(edge.edge1));
            }
            for (Node n : allNodes.get(edge.edge2).outGoingNodes){
                 n.addOutGoingNodes(allNodes.get(edge.edge2));
            }
        }
    }

    public void makeٌRandomDirectedEdges() {
        makeEdgesUndirected();
        for (Edge edge : roadEdges) {
            if(!edge.isDirected){
                boolean randomInt = random.nextBoolean();
                if(randomInt){
                    // Out Going from node 2 to node 1
                    edge.toV = edge.edge1;
                    edge.fromV = edge.edge2;
                    edge.isDirected = true;
                    allNodes.get(edge.edge1).deleteOutGoingNode((allNodes.get(edge.edge2)));
                } else {
                    // Out Going from node 1 to node 2
                    edge.toV = edge.edge2;
                    edge.fromV = edge.edge1;
                    edge.isDirected = true;
                    allNodes.get(edge.edge2).deleteOutGoingNode((allNodes.get(edge.edge1)));
                }
            }
        }
    }

    public Edge getEdge(String v1, String v2) {
        for (Edge e : roadEdges) {
            if (e.edge1.equals(v1) && e.edge2.equals(v2)) {
                return e;
            }
            if (e.edge1.equals(v2) && e.edge2.equals(v1)) {
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

    public Node  getNode(String name) {
        return allNodes.get(name);
    }

}
