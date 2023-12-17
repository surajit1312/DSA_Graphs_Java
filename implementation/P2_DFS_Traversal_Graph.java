package implementation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * CodingNinjas: DFS of Graph
 * 
 * Link:
 * 
 * https://www.codingninjas.com/studio/problems/dfs-traversal_630462
 * https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 * 
 * TC: O(V + E)
 * SC: O(V) - Recursive Stack Space
 */
public class P2_DFS_Traversal_Graph {
    public static void main(String[] args) {
        int v = 5;
        int e = 4;
        int[][] edgeData = { { 0, 2 }, { 0, 1 }, { 1, 2 }, { 3, 4 } };
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < e; i++) {
            edges.add(new ArrayList<>());
            for (int j = 0; j < edgeData[i].length; j++) {
                edges.get(i).add(edgeData[i][j]);
            }
        }
        System.out.println(edges);
        ArrayList<ArrayList<Integer>> dfsPath = depthFirstSearch(v, e, edges);
        System.out.println("DFS traversal on graph : " + dfsPath);
    }

    private static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = getAdjacencyList(v, e, edges);
        ArrayList<ArrayList<Integer>> dfsPath = new ArrayList<ArrayList<Integer>>();
        boolean[] visited = new boolean[v];
        Arrays.fill(visited, false);
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                ArrayList<Integer> currentIterPath = new ArrayList<Integer>();
                dfsGraphHelper(v, adjList, visited, currentIterPath, i);
                dfsPath.add(currentIterPath);
            }
        }
        return dfsPath;
    }

    private static ArrayList<ArrayList<Integer>> getAdjacencyList(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < e; i++) {
            adjList.get(edges.get(i).get(0)).add(edges.get(i).get(1));
            adjList.get(edges.get(i).get(1)).add(edges.get(i).get(0));
        }
        return adjList;
    }

    private static void dfsGraphHelper(int v, ArrayList<ArrayList<Integer>> adjList, boolean[] visited,
            ArrayList<Integer> currentPath, int startIndex) {
        if (visited[startIndex]) {
            return;
        }
        visited[startIndex] = true;
        currentPath.add(startIndex);
        for (int i = 0; i < adjList.get(startIndex).size(); i++) {
            dfsGraphHelper(v, adjList, visited, currentPath, adjList.get(startIndex).get(i));
        }
    }
}
