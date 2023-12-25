package shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * CodingNinjas: Shortest Path in DAG
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/shortest-path-in-dag_8381897
 * 
 * TC:
 * 
 * O(N + M) for Toposort + O(N + M) for edge relaxation O(2N + 2M) ~ O(N + M)
 * 
 * SC:
 * 
 * O(N + M) for adjacency list + O(N) for visited array + O(N) for distance
 * array + O(N) for stack = O(4N + M) ~ O(N + M)
 * 
 */
public class P2_Shortest_Path_Directed_Weighted_Graph_DAG {

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] edges = {
                { 2, 1, 5 },
                { 0, 2, 3 },
                { 0, 1, 2 },
                { 2, 3, 1 }
        };
        int[] minPaths = shortestPathInDAG(n, m, edges);
        System.out.println("Shortest path in DAG : " + Arrays.toString(minPaths));
    }

    public static int[] shortestPathInDAG(int n, int m, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = getAdjacencyList(n, m, edges);
        int[] visited = new int[n];
        Arrays.fill(visited, 0);
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                topoSort(adj, st, visited, i);
            }
        }
        int[] distances = new int[n];
        Arrays.fill(distances, (int) 1e9);
        int startNode = 0;
        distances[startNode] = 0;
        while (!st.empty()) {
            Integer currentNode = st.pop();
            for (Pair it : adj.get(currentNode)) {
                Integer node = it.node;
                Integer weight = it.weight;
                if (distances[currentNode] + weight < distances[node]) {
                    distances[node] = distances[currentNode] + weight;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (distances[i] == 1e9) {
                distances[i] = -1;
            }
        }
        return distances;
    }

    private static void topoSort(ArrayList<ArrayList<Pair>> adj, Stack<Integer> st, int[] visited, int currentNode) {
        visited[currentNode] = 1;
        for (Pair it : adj.get(currentNode)) {
            if (visited[it.node] == 0) {
                topoSort(adj, st, visited, it.node);
            }
        }
        st.add(currentNode);
    }

    private static ArrayList<ArrayList<Pair>> getAdjacencyList(int n, int m, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
        }
        return adj;
    }

    static class Pair {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
