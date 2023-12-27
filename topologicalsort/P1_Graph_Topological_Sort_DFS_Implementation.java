package topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class P1_Graph_Topological_Sort_DFS_Implementation {
    public static void main(String[] args) {
        int v = 5;
        int e = 4;
        int[][] edges = { { 0, 2 }, { 1, 2 }, { 3, 1 }, { 0, 4 } };
        List<Integer> topologicalSortList = topologicalSort(edges, e, v);
        System.out.println("Topological sort for the graph : " + topologicalSortList);
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] edges, int v, int e) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
        }
        return adjList;
    }

    public static List<Integer> topologicalSort(int[][] edges, int e, int v) {
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(edges, v, e);
        List<Integer> result = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        int[] visited = new int[v];
        Arrays.fill(visited, 0);
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                dfsGraphHelper(i, adj, stack, visited);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void dfsGraphHelper(int index, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack,
            int[] visited) {
        visited[index] = 1;
        for (Integer it : adj.get(index)) {
            if (visited[it] == 0) {
                dfsGraphHelper(it, adj, stack, visited);
            }
        }
        stack.push(index);
    }
}
