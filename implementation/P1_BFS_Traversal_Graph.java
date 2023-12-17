package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * GeeksForGeeks: BFS of Graph
 * 
 * Link: https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 * 
 * TC: O(V + E) - where V is number of vertices and E is number of edges
 * SC: O(V + E) - where V is number of vertices and E is number of edges
 * 
 */
public class P1_BFS_Traversal_Graph {

    static class Pair {
        int value;
        ArrayList<Integer> nodes;

        public Pair(int value, ArrayList<Integer> nodes) {
            this.value = value;
            this.nodes = nodes;
        }
    }

    public static void main(String[] args) {
        int V = 10;
        int E = 13;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 0, 8 }, { 1, 5 }, { 1, 6 }, { 1, 9 }, { 2, 4 }, { 3, 7 },
                { 3, 8 }, { 5, 8 }, { 6, 7 }, { 6, 9 } };
        ArrayList<ArrayList<Integer>> adj = createAdjListFromEdges(edges, V, E);
        System.out.println(adj);
        ArrayList<Integer> bfsTraversal = bfsOfGraph(V, adj);
        System.out.println("BFS Traversal on graph : " + bfsTraversal);
    }

    private static ArrayList<ArrayList<Integer>> createAdjListFromEdges(int[][] edges, int V, int E) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < E; i++) {
            adj.get(edges[i][0]).add((Integer) edges[i][1]);
        }
        return adj;
    }

    private static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> bfsPath = new ArrayList<Integer>();
        Arrays.fill(visited, false);
        bfsPathTraversal(V, adj, 0, bfsPath, visited);
        return bfsPath;
    }

    private static void bfsPathTraversal(int V, ArrayList<ArrayList<Integer>> adj, int index,
            ArrayList<Integer> bfsPath, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(index, adj.get(index)));
        visited[index] = true;

        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            bfsPath.add(currentPair.value);
            for (Integer it : currentPair.nodes) {
                if (!visited[it]) {
                    visited[it] = true;
                    queue.offer(new Pair(it, adj.get(it)));
                }
            }
        }
    }
}