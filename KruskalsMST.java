import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KruskalsMST {

    static class Edge {
        int source;
        int aim;
        int weight;

        public Edge(int source, int aim, int weight) {
            this.source = source;
            this.aim = aim;
            this.weight = weight;
        }
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    public static void kruskals(int V, ArrayList<Edge> edges) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.addAll(edges);

        DisjointSet ds = new DisjointSet(V);
        Set<Edge> mst = new HashSet<>();

        while (!pq.isEmpty() && mst.size() < V - 1) {
            Edge edge = pq.poll();
            int sourceParent = ds.find(edge.source);
            int aimParent = ds.find(edge.aim);
            if (sourceParent != aimParent) {
                mst.add(edge);
                ds.union(sourceParent, aimParent);
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.aim + " : " + edge.weight);
        }
    }
}
