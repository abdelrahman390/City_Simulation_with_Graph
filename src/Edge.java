public class Edge implements Comparable<Edge>{
    int x1, y1, x2, y2;
    double weight;
    String edge1;
    String edge2;
    boolean isDirected;
    String fromV;
    String toV;

    public Edge(int x1, int y1, int x2, int y2, String edge1, String edge2, double weight) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight == o.weight){
            return 0;
        } else if(this.weight > o.weight){
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Edge{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", weight=" + weight +
                ", edge1='" + edge1 + '\'' +
                ", edge2='" + edge2 + '\'' +
                ", isDirected=" + isDirected +
                ", fromV='" + fromV + '\'' +
                ", toV='" + toV + '\'' +
                '}';
    }
}
