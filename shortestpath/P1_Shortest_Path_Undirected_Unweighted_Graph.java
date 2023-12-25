package shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/single-source-shortest-path_8416371
 * 
 * TC:
 * 
 * O(N + M) for creating adjacency list, O(N + 2M) for adjacency list BFS,
 * O(N) for path array = O(3N + 3M) ~ O(N + M)
 * 
 * SC:
 * 
 * O(N) for Queue, O(N) for storing Path, O(N + 2M) for adjacency list =
 * O(3N + 2M) ~ O(N + M)
 */
public class P1_Shortest_Path_Undirected_Unweighted_Graph {
    public static void main(String[] args) {
        int[][] edges = {
                { 0, 1 }, { 0, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 },
                { 1, 2 }, { 2, 6 }, { 6, 7 }, { 7, 8 }, { 6, 8 }
        };
        int n = 9;
        int m = 10;
        int src = 0;
        int[] minPaths = shortestPath(edges, n, m, src);
        System.out.println(
                "Shortest paths in the graph from source node '" + src + "' is : " + Arrays.toString(minPaths));
    }

    private static int[] shortestPath(int[][] edges, int n, int m, int src) {
        ArrayList<ArrayList<Integer>> adj = getAdjacencyList(edges, n, m);
        System.out.println(adj);
        int[] paths = new int[n];
        Arrays.fill(paths, -1);
        Queue<Pair> queue = new LinkedList<Pair>();
        for (int i = 0; i < n; i++) {
            if (i == src && paths[i] == -1) {
                queue.offer(new Pair(src, 0));
                break;
            }
        }
        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            if (paths[currentPair.node] == -1) {
                paths[currentPair.node] = currentPair.distance;
            }
            for (Integer it : adj.get(currentPair.node)) {
                if (paths[it] == -1) {
                    queue.offer(new Pair(it, currentPair.distance + 1));
                }
            }
        }
        return paths;
    }

    private static ArrayList<ArrayList<Integer>> getAdjacencyList(int[][] edges, int n, int m) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        return adj;
    }

    static class Pair {
        Integer node;
        int distance;

        public Pair(Integer node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
