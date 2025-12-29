package algorithm.shortestpath.floydWarshall;

import Model.Graph;
import Model.Node;
import algorithm.AlgorithemsResults.AlgorithmResult;
import algorithm.common.RunAlgorithm;
import service.RandomNodeSelector;

import java.util.ArrayList;

public class FloydWarshall implements RunAlgorithm {
    private cachedResult resultCache;

    @Override
    public AlgorithmResult run(Graph g) {

        if(resultCache == null){
            FloydWarshallEngine floydWarshallEngine = new FloydWarshallEngine();
            resultCache = floydWarshallEngine.performAlgorithm(new ArrayList<>(g.getAllNodes().values()));
        }

        Node startNode = RandomNodeSelector.getRandomNode();
        Node endNode = RandomNodeSelector.getDeferentRandomNode(startNode);

        return resultCache.getPath(startNode,  endNode);
    }

    public void dropcache() {
        resultCache = null;
    }

}
