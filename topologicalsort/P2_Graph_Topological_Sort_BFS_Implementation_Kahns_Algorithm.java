package topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GeeksForGeeks: Topological Sorting using BFS (Kahn's Algorithm)
 * 
 * Link: https://practice.geeksforgeeks.org/problems/topological-sort/1
 * 
 * TC: O(V + E)
 * SC: O(2V) ~ O(V)
 */
public class P2_Graph_Topological_Sort_BFS_Implementation_Kahns_Algorithm {
    public static void main(String[] args) {
        int v = 5;
        int e = 4;
        int[][] edges = { { 0, 2 }, { 1, 2 }, { 3, 1 }, { 0, 4 } };
        List<Integer> topologicalSortList = topologicalSort(edges, e, v);
        System.out.println("Topological sort for the graph : " + topologicalSortList);
    }

    // Function to return list containing vertices in Topological order.
    private static List<Integer> topologicalSort(int[][] edges, int e, int v) {
        List<Integer> topoSortList = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> adj = getAdjacencyList(edges, e, v);
        int[] indegrees = getVerticeIndegrees(adj, v);
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            topoSortList.add(current);
            for (Integer it : adj.get(current)) {
                indegrees[it]--;
                if (indegrees[it] == 0) {
                    queue.offer(it);
                }
            }
        }
        return topoSortList;
    }

    private static int[] getVerticeIndegrees(ArrayList<ArrayList<Integer>> adj, int v) {
        int[] indegrees = new int[v];
        Arrays.fill(indegrees, 0);
        for (int i = 0; i < v; i++) {
            for (Integer it : adj.get(i)) {
                indegrees[it]++;
            }
        }
        return indegrees;
    }

    private static ArrayList<ArrayList<Integer>> getAdjacencyList(int[][] edges, int e, int v) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < e; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
        }
        return adj;
    }
}
