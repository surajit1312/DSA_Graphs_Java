package traversals;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * CodingNinjas: Rotten Oranges
 * 
 * Link: https://www.codingninjas.com/studio/problems/rotting-oranges_701655
 * 
 * TC: O(n x m) x 4
 * SC: O(n x m) - for all oranges of grid pushed to Queue
 * 
 */
public class P2_Minimum_Time_To_Rotten_Oranges {
    public static void main(String[] args) {
        int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        int n = 3;
        int m = 3;
        int minTimeTaken = minTimeToRot(grid, n, m);
        System.out.println("Minimum time need to rotten all oranges (in units) : " + minTimeTaken);
    }

    private static int minTimeToRot(int[][] grid, int n, int m) {
        int minTime = 0;
        int[][] visited = new int[n][m];
        int freshOranges = 0;
        int rottenOranges = 0;
        Queue<Pair> queue = new LinkedList<Pair>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = 2;
                    queue.offer(new Pair(i, j, minTime));
                } else {
                    visited[i][j] = 0;
                }
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            minTime = Math.max(minTime, currentPair.time);
            int[] deltaRow = { -1, 0, 1, 0 };
            int[] deltaCol = { 0, 1, 0, -1 };
            int directions = 4;
            for (int i = 0; i < directions; i++) {
                int visitedRow = currentPair.row + deltaRow[i];
                int visitedCol = currentPair.col + deltaCol[i];
                if (visitedRow >= 0 && visitedRow < n && visitedCol >= 0 && visitedCol < m
                        && visited[visitedRow][visitedCol] == 0 && grid[visitedRow][visitedCol] == 1) {
                    visited[visitedRow][visitedCol] = 2;
                    queue.offer(new Pair(visitedRow, visitedCol, minTime + 1));
                    rottenOranges++;
                }
            }
        }
        if (freshOranges != rottenOranges) {
            return -1;
        }
        return minTime;
    }

    static class Pair {
        int row;
        int col;
        int time;

        public Pair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
