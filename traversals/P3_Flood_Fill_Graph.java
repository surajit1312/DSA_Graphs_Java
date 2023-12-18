package traversals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * CodingNinjas: Flood Fill
 * 
 * Link: https://www.codingninjas.com/studio/problems/flood-fill-_1082141
 * 
 * TC: O(n x m + n x m x 4) ~ O(n x m)
 * SC: O(n x m) + O(n x m) ~ O(n x m)
 */
public class P3_Flood_Fill_Graph {
    public static void main(String[] args) {
        int[][] image = { { 7, 1, 1, 1 }, { 1, 7, 7, 7 }, { 7, 7, 7, 0 }, { 7, 7, 7, 4 }, { 4, 4, 4, 4 } };
        int n = 5;
        int m = 4;
        int x = 2;
        int y = 2;
        int p = 5;
        int[][] result = floodFill(image, n, m, x, y, p);
        System.out.println("Flood filled image looks like : " + Arrays.deepToString(result));
    }

    public static int[][] floodFill(int[][] image, int n, int m, int x, int y, int p) {
        int[][] visited = new int[n][m];
        int target = image[x][y];

        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(x, y));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (image[i][j] == target) {
                    visited[i][j] = 0;
                } else {
                    visited[i][j] = image[i][j];
                }
            }
        }

        visited[x][y] = 1;
        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            image[currentPair.row][currentPair.col] = p;
            int[] deltaRow = { -1, 0, 1, 0 };
            int[] deltaCol = { 0, 1, 0, -1 };
            int directions = 4;
            for (int i = 0; i < directions; i++) {
                int visitedRow = currentPair.row + deltaRow[i];
                int visitedCol = currentPair.col + deltaCol[i];
                if (visitedRow >= 0 && visitedRow < n && visitedCol >= 0 && visitedCol < m
                        && visited[visitedRow][visitedCol] == 0 && image[visitedRow][visitedCol] == target) {
                    visited[visitedRow][visitedCol] = 1;
                    queue.offer(new Pair(visitedRow, visitedCol));
                }
            }
        }
        return image;
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
