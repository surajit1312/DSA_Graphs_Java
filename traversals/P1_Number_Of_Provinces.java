package traversals;

import java.util.Arrays;

/**
 * 
 * GeeksForGeeks: Number of Provinces
 * 
 * Link: https://www.geeksforgeeks.org/problems/number-of-provinces/1
 * 
 * TC: O(2V + 2E)
 * SC: O(2V)
 * 
 */
public class P1_Number_Of_Provinces {
    public static void main(String[] args) {
        int[][] roads = { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } };
        int n = 3;
        int countProvinces = findNumOfProvinces(roads, n);
        System.out.println("Number of provinces found : " + countProvinces);
    }

    private static int findNumOfProvinces(int[][] roads, int n) {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfsGraphHelper(roads, visited, i);
            }
        }
        return count;
    }

    private static void dfsGraphHelper(int[][] roads, boolean[] visited, int startIndex) {
        if (visited[startIndex]) {
            return;
        }
        visited[startIndex] = true;
        int n = roads[startIndex].length;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && i != startIndex && roads[startIndex][i] == 1) {
                visited[i] = true;
                dfsGraphHelper(roads, visited, i);
            }
        }
    }
}
