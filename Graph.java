import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Graph {

    private LinkedList<Vertex> vertexes;

    public Graph() {
        vertexes = new LinkedList<>();
    }

    public void addVertex(int id) {
        if (findVertex(id) != null) {
            System.out.println("Wierzchołek o identyfikatorze " + id + " już istnieje");
        }else{
            vertexes.add(new Vertex(id));
        }

    }

    public void addEdge(int weight, int source, int aim) {

        if (weight <= 0) {
            System.out.println("Waga połączenia musi być dodatnia");
        } else {
            Vertex vertexSource = findVertex(source);
            Vertex vertexAim = findVertex(aim);

            if (vertexSource == null || vertexAim == null) {
                System.out.println("Wierzchołek o podanym identyfikatorze nie istnieje");
            } else {

                vertexSource.addEdge(new Edge(weight, aim, source));
                vertexAim.addEdge(new Edge(weight, source, aim));

            }
        }
    }

    public void printGraph() {
        for (Vertex vertex : vertexes) {
            System.out.println("Node " + vertex.id + ":");
            for (Edge connection : vertex.edges) {
                System.out.println(connection.source +"  <-> " + connection.aim + " (waga: " + connection.weight + ")");
            }
        }
    }

    private Vertex findVertex(int id) {
        for (Vertex vertex : vertexes) {
            if (vertex.id == id) {
                return vertex;
            }
        }
        return null;
    }

    private class Vertex {

        int id;
        LinkedList<Edge> edges;
        boolean visited;
        int color;

        public Vertex(int id) {
            this.id = id;
            edges = new LinkedList<>();
            this.visited = false;
            this.color = -1;
        }

        public void addEdge(Edge connection) {
            edges.add(connection);
        }
    }

    private class Edge {

        int weight;
        int source;
        int aim;

        public Edge(int weight, int source, int aim) {
            this.weight = weight;
            this.source = source;
            this.aim = aim;
        }
    }

    public void findMinimumSpanningTree() {
        int V = vertexes.size();
        ArrayList<KruskalsMST.Edge> graphEdges = new ArrayList<>();


        for (Vertex vertex : vertexes) {
            for (Edge edge : vertex.edges) {
                graphEdges.add(new KruskalsMST.Edge(edge.source, edge.aim, edge.weight));
            }
        }

        graphEdges.sort(new Comparator<KruskalsMST.Edge>() {
            @Override
            public int compare(KruskalsMST.Edge o1, KruskalsMST.Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        KruskalsMST.kruskals(V, graphEdges);
    }

    public int findChromaticNumber() {
        int numColors = 0;
        for (Vertex vertex : vertexes) {
            if (!vertex.visited) {
                vertex.color = colorUtil(vertex, numColors);
                numColors = Math.max(numColors, vertex.color + 1);
            }
        }
        return numColors;
    }

    private int colorUtil(Vertex vertex, int currentColor) {
        vertex.visited = true;
        for (Edge edge : vertex.edges) {
            Vertex neighbor = findVertex(edge.aim);
            if (!neighbor.visited && neighbor.color == currentColor) {
                currentColor++;
                currentColor = colorUtil(neighbor, currentColor);
            }
        }
        return currentColor;
    }

}
