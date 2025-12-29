package algorithm.MST.kruskals;

import Model.Graph;
import algorithm.AlgorithemsResults.AlgorithmResult;
import algorithm.common.RunAlgorithm;

public class kruskalsAlgo implements RunAlgorithm {

    @Override
    public AlgorithmResult run(Graph g) {
        KruskalsEngine  kruskalsEngine = new KruskalsEngine();

        return kruskalsEngine.performAlgorithm(g);
    }
}
