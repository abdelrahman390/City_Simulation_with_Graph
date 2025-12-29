package algorithm.MST.kruskals;

class nodeStruct {
    String name;
    nodeStruct parent;
    int rank;

    public nodeStruct(String name, nodeStruct parent, int rank) {
        this.name = name;
        this.parent = parent;
        this.rank = rank;
    }
}
