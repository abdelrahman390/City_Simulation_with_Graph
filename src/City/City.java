package City;

import Model.Graph;
import algorithm.AlgorithemsResults.AlgorithmResult;
import algorithm.MST.kruskals.kruskalsAlgo;
import algorithm.shortestpath.dijkstra.Dijkstra;
import algorithm.shortestpath.floydWarshall.FloydWarshall;
import graphBuilder.CityGraphBuilder;

public class City {
    // Class City has one responsibility to get or create one City class that will contain the Graph pointer
    private static City instance;
    private final Graph graph;

    private City() {
        CityGraphBuilder builder = new CityGraphBuilder();
        this.graph = builder.build();
//        instance = this;
    }

    public static City getCityInstance() {
        if (instance == null) {
            instance = new City();
        }
        return instance;
    }

    public Graph getGraph() {
        return graph;
    }

    public AlgorithmResult runDijkstra(){
        Dijkstra dijkstra = new Dijkstra();
        return dijkstra.run(graph);
    }

    public AlgorithmResult runKruskals(){
        kruskalsAlgo kruskal = new kruskalsAlgo();
        return kruskal.run(graph);
    }

    public AlgorithmResult runFloydWarshall() {
        FloydWarshall floydWarshall = new FloydWarshall();
        return floydWarshall.run(graph);
    }

    public void deleteCachedPreComputedData() {
        FloydWarshall floydWarshall = new FloydWarshall();
        floydWarshall.dropcache();
    }
}