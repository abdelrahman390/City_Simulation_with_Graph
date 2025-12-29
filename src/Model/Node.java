package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private final String name;
    private final int x;
    private final int y;
    private final ArrayList <Node> outGoingNodes = new ArrayList<>();

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public ArrayList <Node>  getOutGoingNodes() {
        return outGoingNodes;
    }

    public void addOutGoingNodes(Node n) {
        if(!outGoingNodes.contains(n))
            outGoingNodes.add(n);
    }

//    public void deleteAllOutGoingNodes() {
//        outGoingNodes.clear();
//    }

    public void deleteOutGoingNode(Node n) {
        outGoingNodes.remove(n);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node{name='").append(name)
                .append("', x=").append(x)
                .append(", y=").append(y)
                .append(", outGoingNodes=[");

        for (int i = 0; i < outGoingNodes.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(outGoingNodes.get(i).name);  // Only print node names
        }

        sb.append("]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y && Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y);
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}