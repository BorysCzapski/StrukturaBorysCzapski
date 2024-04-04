import java.util.LinkedList;

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
                System.out.println("Wierzchołek o podanym identyfikatorze " + vertexSource+ " nie istnieje");
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

        public Vertex(int id) {
            this.id = id;
            edges = new LinkedList<>();
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
}

