public class Main {

    public static void main(String[] args) {
        Graph graf = new Graph();

        graf.addVertex(0);
        graf.addVertex(1);
        graf.addVertex(2);
        graf.addVertex(3);

        graf.addEdge(4, 0, 1);
        graf.addEdge(3, 0, 2);
        graf.addEdge(2, 1, 3);
        graf.addEdge(7, 1, 2);
        graf.addEdge(6, 2, 3);
        graf.addEdge(6, 0, 3);

        graf.printGraph();

        graf.findMinimumSpanningTree();
    }
}
