package algorithm.shortestpath.dijkstra;

import Model.*;
import algorithm.AlgorithemsResults.AlgorithmResult;
import algorithm.common.RunAlgorithm;
import service.RandomNodeSelector;

import java.util.*;

public class Dijkstra implements RunAlgorithm {

    public AlgorithmResult run(Graph g) {
        ArrayList<Node> nodes = new ArrayList<Node>(g.getAllNodes().values());
        Node start = RandomNodeSelector.getRandomNode();
        Node target = RandomNodeSelector.getDeferentRandomNode(start);

        DijkstraEngine dijkstraEngine = new DijkstraEngine();
        return dijkstraEngine.performAlgorithm(nodes, start, target, g);
    }
}