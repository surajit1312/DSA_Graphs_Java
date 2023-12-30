package topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * CodingNinjas: Alien dictionary
 * 
 * Link: https://www.codingninjas.com/studio/problems/alien-dictionary_630423
 * 
 * TC: O(n x k) (to create adjacency list) + O (k + e) (BFS algorithm) where k =
 * number of nodes and e = number of edges
 * O(n x k) + O(k + e)
 * 
 * SC: O(k) for adjacency list, O(k) (for queue) + O(k) (for indegrees) + O(k)
 * (for topo sort list)
 * O(4k) ~ O(k)
 * 
 */
public class P5_Alien_Dictionary {
    public static void main(String[] args) {
        String[] dictionary = { "baa", "abcd", "abca", "cab", "cad" };
        int k = 4;
        String alienLanguageOrder = getAlienLanguage(dictionary, k);
        System.out.println("Alien dictionary follows the order : " + alienLanguageOrder);
    }

    private static String getAlienLanguage(String[] dictionary, int k) {
        String language = "";
        ArrayList<Integer> topoSortList = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> adj = getAdjancencyList(dictionary, k);
        int[] indegrees = getInDegrees(adj, k);
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
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
        for (Integer it : topoSortList) {
            language += (char) (it + (int) 'a');
        }
        return language;
    }

    private static int[] getInDegrees(ArrayList<ArrayList<Integer>> adj, int v) {
        int[] indegrees = new int[v];
        for (int i = 0; i < v; i++) {
            for (Integer it : adj.get(i)) {
                indegrees[it]++;
            }
        }
        return indegrees;
    }

    private static ArrayList<ArrayList<Integer>> getAdjancencyList(String[] dictionary, int k) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < k; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < dictionary.length - 1; i++) {
            int len = Math.min(dictionary[i].length(), dictionary[i + 1].length());
            for (int p = 0; p < len; p++) {
                if (dictionary[i].charAt(p) != dictionary[i + 1].charAt(p)) {
                    adj.get(dictionary[i].charAt(p) - 'a').add(dictionary[i + 1].charAt(p) - 'a');
                    break;
                }
            }
        }
        return adj;
    }
}
