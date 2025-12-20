import java.util.ArrayList;

public class returnTwoValuesEdgesAndDouble {
    ArrayList<Edge> arrayList;
    double doubleValue;

    public returnTwoValuesEdgesAndDouble(ArrayList<Edge> path, double totalDistance) {
        this.arrayList = path;
        this.doubleValue = totalDistance;
    }
}