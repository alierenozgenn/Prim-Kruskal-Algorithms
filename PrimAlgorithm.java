import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int V;
    LinkedList<Edge>[] adjList;

    @SuppressWarnings("unchecked")
    Graph(int V) {
        this.V = V;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest, int weight) {
        adjList[src].add(new Edge(src, dest, weight));
        adjList[dest].add(new Edge(dest, src, weight));
    }

    void primMST() {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.weight));

        Arrays.fill(key, Integer.MAX_VALUE);
        pq.add(new Edge(-1, 0, 0));
        key[0] = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;
            inMST[u] = true;

            for (Edge edge : adjList[u]) {
                if (!inMST[edge.dest] && edge.weight < key[edge.dest]) {
                    key[edge.dest] = edge.weight;
                    pq.add(new Edge(u, edge.dest, key[edge.dest]));
                    parent[edge.dest] = u;
                }
            }
        }

        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }
}

public class PrimAlgorithm {
    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}
