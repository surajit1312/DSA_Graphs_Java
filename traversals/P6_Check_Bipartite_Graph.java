package traversals;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * GeeksForGeeks: Bipartite Graph
 * 
 * Link: https://practice.geeksforgeeks.org/problems/bipartite-graph/1
 * 
 * TC: O(V + 2E) ~ O(V + E)
 * SC: O(3V) ~ O(V)
 * 
 */
public class P6_Check_Bipartite_Graph {
    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 0, 2 } };
        int V = 3;
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(edges, V);
        System.out.println("Is Graph Bipartite : " + isBipartite(V, adj));
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] edges, int v) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }
        return adjList;
    }

    private static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);
        for (int i = 0; i < color.length; i++) {
            if (color[i] == -1) {
                if (!dfsGraphIfBipartite(adj, color, i, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfsGraphIfBipartite(ArrayList<ArrayList<Integer>> adj, int[] color, int index,
            int colorvalue) {
        color[index] = colorvalue;
        for (Integer it : adj.get(index)) {
            if (color[it] == -1) {
                colorvalue = colorvalue == 0 ? 1 : 0;
                if (!dfsGraphIfBipartite(adj, color, it, colorvalue)) {
                    return false;
                }
            } else if (color[it] == colorvalue) {
                return false;
            }
        }
        return true;
    }
}
