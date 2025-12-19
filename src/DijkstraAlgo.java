import java.util.*;

class DijkstraAlgo {
    // return ArrayList<nodes>
    public static returnTwoValuesEdgesAndDouble getShortestPath(ArrayList<Node> nodes, Node Start, Node Target){
        ArrayList<Node> resultNodes = new ArrayList<>();

        Set<DijkNodeClass> vistedNodes = new HashSet<>();
        Map<String, DijkNodeClass> allNodes = new HashMap<String, DijkNodeClass>();
        PriorityQueue<DijkNodeClass> priorityQueue = new PriorityQueue<>();

        for(Node n : nodes){
            if(n == Start){
                DijkNodeClass DN = new DijkNodeClass(n, 0);
                priorityQueue.add(DN);
                allNodes.put(n.name, DN);
            } else {
                n.isEndNode = false;
                n.isStartNode = false;
                DijkNodeClass DN = new DijkNodeClass(n, Double.MAX_VALUE);
                allNodes.put(n.name, DN);
            }

            if(n == Start){
                n.isStartNode = true;
                n.isEndNode = false;
            } else if(n == Target){
                n.isEndNode = true;
            }
        }
//        System.out.println("Start from: " + Start + " to: " + Target);

        while(!priorityQueue.isEmpty()){
            DijkNodeClass node = priorityQueue.poll();

            if(vistedNodes.contains(allNodes.get(node.node.name))) continue;
            vistedNodes.add(node);

            ArrayList<Node> neighbors = node.node.getOutGoingNodes();
            for (Node n : neighbors){
                if (vistedNodes.contains(allNodes.get(n.name))) continue;

                DijkNodeClass next = allNodes.get(n.name);
                    double currToNextCost = City.getDistance(node.node.x, node.node.y, n.x, n.y);

//                    System.out.println( n.cost + " > " + node.cost + currToNextCost );
//                allNodes.get(n.name).cost
                    if(next.cost > node.cost + currToNextCost){
                        next.cost = node.cost + currToNextCost;
                        next.prev = node.node;
                        priorityQueue.add(allNodes.get(n.name));
                    }
            }
        }

        double dis = 0;
        if(Double.MAX_VALUE != allNodes.get(Target.name).cost){
            Node curr = Target;
            dis = allNodes.get(Target.name).cost;
            while (curr != null && curr != Start) {
                resultNodes.add(curr);
                curr = allNodes.get(curr.name).prev;
            }
            resultNodes.add(Start);
        }

        ArrayList<Edge> theFinalEdges = new ArrayList<>();
        if(!resultNodes.isEmpty()){
            City c = City.getCityInstance();
            for (int i = 0; i < resultNodes.size()-1; i++) {
                Edge newEdge = c.getEdge(resultNodes.get(i).name, resultNodes.get(i+1).name);
                if(newEdge != null){
                    theFinalEdges.add(newEdge);
                }
            }
        }
//        return resultNodes;
        return new returnTwoValuesEdgesAndDouble(theFinalEdges, dis);
    }
}

class DijkNodeClass implements Comparable<DijkNodeClass>{
    Node node;
    Node prev;
    double cost;

    public DijkNodeClass(Node node, double cost) {
        this.node = node;
        this.cost = cost;
//        if(cost == 0){
            prev = node;
//        }
    }

    @Override
    public int compareTo(DijkNodeClass o) {
        if(cost < o.cost){
            return -1;
        } else if(cost > o.cost){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DijkNodeClass)) return false;
        DijkNodeClass that = (DijkNodeClass) o;
        return node.name.equals(that.node.name);
    }

    @Override
    public int hashCode() {
        return node.name.hashCode();
    }

    @Override
    public String toString() {
        return node.name + " ( cost: " + cost + ")" + " , Prev: " + prev;
    }
}