package Model;

public class Edge implements Comparable<Edge>{
    private final int x1, y1, x2, y2;
    private final double weight;
    private final String edge1;
    private final String edge2;
    private boolean isDirected;
    private String fromV;
    private String toV;

    public Edge(int x1, int y1, int x2, int y2, String edge1, String edge2, double weight) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.weight = weight;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public double getWeight() {
        return weight;
    }

    public String getEdge1() {
        return edge1;
    }

    public String getEdge2() {
        return edge2;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public String getFromV() {
        return fromV;
    }

    public String getToV() {
        return toV;
    }

//    public void setEdge1(String edge1) {
//        this.edge1 = edge1;
//    }

//    public void setEdge2(String edge2) {
//        this.edge2 = edge2;
//    }

//    public void setWeight(double weight) {
//        this.weight = weight;
//    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public void setFromV(String fromV) {
        this.fromV = fromV;
    }

    public void setToV(String toV) {
        this.toV = toV;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
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
