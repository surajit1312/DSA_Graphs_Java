package topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * CodingNinjas: Detect Cycle In A Directed Graph
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/detect-cycle-in-a-directed-graph_1062626
 * 
 * TC: O(V + E)
 * SC: O(2V) ~ O(V)
 * 
 */
public class P3_Detect_Cycle_In_Directed_Graph {
    public static void main(String[] args) {
        int n = 5;
        int[][] edgeArr = {
                { 1, 2 },
                { 4, 1 },
                { 2, 4 },
                { 3, 4 },
                { 5, 2 },
                { 1, 3 }
        };
        ArrayList<ArrayList<Integer>> edges = getEdges(edgeArr, n);
        boolean hasCycle = detectCycleInDirectedGraph(n, edges);
        System.out.println("Directed Graph has cycle : " + hasCycle);
    }

    private static boolean detectCycleInDirectedGraph(int n, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adj = getAdjacencyList(edges, edges.size(), n);
        int[] indegrees = getVerticeIndegrees(adj, n);
        ArrayList<Integer> topoSort = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            topoSort.add(current);
            for (Integer it : adj.get(current)) {
                indegrees[it]--;
                if (indegrees[it] == 0) {
                    queue.add(it);
                }
            }
        }
        return topoSort.size() < n;
    }

    private static ArrayList<ArrayList<Integer>> getEdges(int[][] edgeArr, int n) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < edgeArr.length; i++) {
            ArrayList<Integer> edge = new ArrayList<Integer>();
            for (int j = 0; j < edgeArr[i].length; j++) {
                edge.add(edgeArr[i][j]);
            }
            edges.add(edge);
        }
        return edges;
    }

    private static int[] getVerticeIndegrees(ArrayList<ArrayList<Integer>> adj, int v) {
        int[] indegrees = new int[v + 1];
        Arrays.fill(indegrees, 0);
        for (int i = 0; i <= v; i++) {
            for (Integer it : adj.get(i)) {
                indegrees[it]++;
            }
        }
        return indegrees;
    }

    private static ArrayList<ArrayList<Integer>> getAdjacencyList(ArrayList<ArrayList<Integer>> edges, int e, int v) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < e; i++) {
            adj.get(edges.get(i).get(0)).add(edges.get(i).get(1));
        }
        return adj;
    }
}
