package algorithm.shortestpath.dijkstra;

import Model.Edge;
import Model.Graph;
import Model.Node;
import algorithm.AlgorithemsResults.AlgorithmResult;

import java.util.*;

class DijkstraEngine {
    public AlgorithmResult performAlgorithm(ArrayList<Node> nodes, Node startNode,Node targetNode, Graph g) {
        ArrayList<Node> resultNodes = new ArrayList<>();

        Set<DijkstraNode> visitedNodes = new HashSet<>();
        Map<String, DijkstraNode> allNodes = new HashMap<String, DijkstraNode>();
        PriorityQueue<DijkstraNode> priorityQueue = new PriorityQueue<>();

        for(Node n : nodes){
            if(n == startNode){
                DijkstraNode DN = new DijkstraNode(n, 0);
                priorityQueue.add(DN);
                allNodes.put(n.getName(), DN);
            } else {
                DijkstraNode DN = new DijkstraNode(n, Double.MAX_VALUE);
                allNodes.put(n.getName(), DN);
            }
        }

        while(!priorityQueue.isEmpty()){
            DijkstraNode node = priorityQueue.poll();

            if(visitedNodes.contains(allNodes.get(node.node.getName()))) continue;
            visitedNodes.add(node);

            ArrayList<Node> neighbors = node.node.getOutGoingNodes();
            for (Node n : neighbors){
                if (visitedNodes.contains(allNodes.get(n.getName()))) continue;

                DijkstraNode next = allNodes.get(n.getName());

                double currToNextCost = g.getEdge(node.node.getName(), n.getName()).getWeight();

                if(next.cost > node.cost + currToNextCost){
                    next.cost = node.cost + currToNextCost;
                    next.prev = node.node;
                    priorityQueue.add(allNodes.get(n.getName()));
                }
            }
        }

        double dis = 0;
        if(Double.MAX_VALUE != allNodes.get(targetNode.getName()).cost){
            Node curr = targetNode;
            dis = allNodes.get(targetNode.getName()).cost;
            while (curr != null && curr != startNode) {
                resultNodes.add(curr);
                curr = allNodes.get(curr.getName()).prev;
            }
            resultNodes.add(startNode);
        }

        ArrayList<Edge> theFinalEdges = new ArrayList<>();
        if(!resultNodes.isEmpty()){
            for (int i = 0; i < resultNodes.size()-1; i++) {
                Edge newEdge = g.getEdge(resultNodes.get(i).getName(), resultNodes.get(i+1).getName());
                if(newEdge != null){
                    theFinalEdges.add(newEdge);
                }
            }
        }

        return new AlgorithmResult(theFinalEdges, dis, startNode, targetNode);
    }
}
