package shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * CodingNinjas: Dijkstra's shortest path
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/dijkstra's-shortest-path_985358
 * 
 * TC: O(Elog(V))
 * SC: O(E + V)
 * 
 */
public class P3_Dijkstras_Shortest_Path_Algorithm {
    public static void main(String[] args) {
        int[][] edge = {
                { 0, 1, 5 },
                { 0, 2, 8 },
                { 1, 2, 9 },
                { 1, 3, 2 },
                { 2, 3, 6 }
        };
        int vertices = 4;
        int edges = 5;
        int source = 0;
        List<Integer> shortestPaths = dijkstra(edge, vertices, edges, source);
        System.out.println("Dijkstra's Shortest Path of graph from source node '" + source + "' is : " + shortestPaths);
    }

    private static List<Integer> dijkstra(int[][] edge, int vertices, int edges, int source) {
        ArrayList<ArrayList<Edge>> adj = getAdjacencyList(edge, vertices, edges);
        List<Integer> paths = new ArrayList<Integer>();
        int[] distances = new int[vertices];
        Arrays.fill(distances, (int) 1e9);
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);
        for (int i = 0; i < vertices; i++) {
            if (source == i) {
                distances[i] = 0;
                pq.offer(new Pair(0, i));
                break;
            }
        }

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int currentNode = pair.node;
            int currentDistance = pair.distance;
            for (Edge it : adj.get(currentNode)) {
                int edgeWeight = it.weight;
                int adjNode = it.node;
                if (currentDistance + edgeWeight < distances[adjNode]) {
                    distances[adjNode] = currentDistance + edgeWeight;
                    pq.offer(new Pair(distances[adjNode], adjNode));
                }
            }
        }
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == 1e9) {
                distances[i] = -1;
            }
            paths.add(distances[i]);
        }
        return paths;
    }

    private static ArrayList<ArrayList<Edge>> getAdjacencyList(int[][] edge, int vertices, int edges) {
        ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < edges; i++) {
            adj.get(edge[i][0]).add(new Edge(edge[i][1], edge[i][2]));
            adj.get(edge[i][1]).add(new Edge(edge[i][0], edge[i][2]));
        }
        return adj;
    }

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Pair {
        int distance;
        int node;

        public Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }
}
