import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

class FloydWarshall {
    private Map<String, Integer> NodesIndex = new HashMap<>();
    double[][] distMatrices;
    String[][] prevNodes;
    private static FloydWarshall instance;

    private FloydWarshall(){};

    private ArrayList<Edge> reconstructPath(String start, String end) {
        ArrayList<String> path = new ArrayList<>();
        String current = end;

        Integer startIdx = NodesIndex.get(start);
        Integer endIdx = NodesIndex.get(end);

        // Reconstruct path
        while (current != null && !current.equals(start)) {
            path.add(current);
            Integer currentIdx = NodesIndex.get(current);
            current = prevNodes[startIdx][currentIdx];
        }

        if (current == null) {
            System.out.println("No path from " + start + " to " + end + ".");
            return null;
        }

        path.add(start);
        Collections.reverse(path);
        ArrayList<Edge> resArray = new ArrayList<>();
        City c = City.getCityInstance();
        for (int i = 0; i < path.size()-1; i++) {
            Edge e = c.getEdge(path.get(i), path.get(i+1));
            if(e != null){
                resArray.add(e);
            } else {
                return null;
            }
        }

        // Print the path
        System.out.println(String.join(" -> ", path));
        return resArray;
    }

    public static FloydWarshall getInstance(){
        if(instance == null){
            instance = new FloydWarshall();
        }
        return instance;
    }

    public void deleteInstance() {
        instance = null;
    }

    public returnTwoValuesEdgesAndDouble getRandomShortestRoadFloydWarshall(ArrayList<Node> nodes){
        if(nodes.isEmpty()) return null;
        if(NodesIndex.isEmpty()){
            PreComputeFloydWarshall(nodes);
        }

        returnTwoValuesEdgesAndDouble res = null;
        Random rand = new Random();
        List<String> keys = new ArrayList<>(NodesIndex.keySet());
        String randomKey = keys.get(rand.nextInt(keys.size()));
        String ramdomStartNode = keys.get(rand.nextInt(keys.size()));
        String ramdomEndNode = keys.get(rand.nextInt(keys.size()));
        while(ramdomStartNode.equals(ramdomEndNode)){
            ramdomStartNode = keys.get(rand.nextInt(keys.size()));
            ramdomEndNode = keys.get(rand.nextInt(keys.size()));
        }
        System.out.println(ramdomStartNode + " -> " + ramdomEndNode);

//        Duplicated code
        for(Node n : nodes){
            if(n.name.equals(ramdomStartNode)){
                n.isStartNode = true;
                n.isEndNode = false;
            } else if(n.name.equals(ramdomEndNode)){
                n.isStartNode = false;
                n.isEndNode = true;
            } else {
                n.isStartNode = false;
                n.isEndNode = false;
            }
        }

        res = new returnTwoValuesEdgesAndDouble(reconstructPath(ramdomStartNode, ramdomEndNode), distMatrices[NodesIndex.get(ramdomStartNode)][NodesIndex.get(ramdomEndNode)]);

        return res;
    }

    public double[][] PreComputeFloydWarshall(ArrayList<Node> nodes){
        int n = nodes.size();
        distMatrices = new double[n][n];
        prevNodes = new String[n][n];

        System.out.println(NodesIndex.isEmpty());
        if(!NodesIndex.isEmpty()){

        }

        City c = City.getCityInstance();

        for (int i = 0; i < n; i++) {
            NodesIndex.put(nodes.get(i).name, i);
        }
//        System.out.println("##############");
//        System.out.println(NodesIndex);

        // initializing
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j){
                    distMatrices[i][j] = 0;
                } else {
                    Edge e = c.getEdge(nodes.get(i).name, nodes.get(j).name);
                    if(e != null){
                        if(e.isDirected && e.fromV.equals(nodes.get(i).name) &&  e.toV.equals(nodes.get(j).name)){
                            distMatrices[i][j] = e.weight;
                            prevNodes[i][j] = nodes.get(i).name; // ✅ predecessor
                        } else if(!e.isDirected) {
                            distMatrices[i][j] = e.weight;
                            prevNodes[i][j] = nodes.get(i).name; // ✅ predecessor
                        } else {
                            distMatrices[i][j] = Double.POSITIVE_INFINITY;
                        }
                    } else {
                        distMatrices[i][j] = Double.POSITIVE_INFINITY;
                    }
//                    System.out.println("-----------");
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.print(distMatrices[i][j] + " ");
            }
//            System.out.println();
        }

        // Main Floyd–Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distMatrices[i][k] + distMatrices[k][j] < distMatrices[i][j]) {
                        distMatrices[i][j] = distMatrices[i][k] + distMatrices[k][j];
                        prevNodes[i][j] = prevNodes[k][j];
                    }
                }
            }
        }

        return distMatrices;
    }

}
