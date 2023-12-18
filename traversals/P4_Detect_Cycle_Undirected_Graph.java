package traversals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * GeeksForGeeks: Detect cycle in an undirected graph
 * 
 * Link:
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 * 
 * TC: O(2V + 2E) ~ O(V + E)
 * SC: O(2V) ~ O(V)
 * 
 */
public class P4_Detect_Cycle_Undirected_Graph {
    public static void main(String[] args) {
        int[][] edges = { { 5, 3 }, { 0, 1 }, { 1, 2 }, { 3, 4 } };
        int V = 6;
        List<List<Integer>> adj = createAdjacencyList(edges, V);
        boolean isCycleDetected = detectCycle(V, adj);
        System.out.println("Is Cycle detected in the Graph : " + isCycleDetected);
    }

    private static List<List<Integer>> createAdjacencyList(int[][] edges, int v) {
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }
        return adjList;
    }

    private static boolean detectCycle(int V, List<List<Integer>> adj) {
        int[] visited = new int[V];
        Arrays.fill(visited, -1);
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == -1 && bfsGraphHelper(i, visited, adj)) {
                return true;
            }
        }
        return false;
    }

    private static boolean bfsGraphHelper(int current, int[] visited, List<List<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(current);
        visited[current] = 0;
        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            visited[currentNode] = 1;
            for (Integer it : adj.get(currentNode)) {
                if (visited[it] == -1) {
                    queue.offer(it);
                    visited[it] = 0;
                } else if (visited[it] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
