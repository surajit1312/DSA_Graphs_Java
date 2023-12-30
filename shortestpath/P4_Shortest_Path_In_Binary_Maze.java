package shortestpath;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * GeeksForGeeks: Shortest Distance in a Binary Maze
 * 
 * Link:
 * 
 * https://practice.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1
 * https://www.codingninjas.com/studio/problems/shortest-path-in-a-binary-maze_893065
 * 
 * TC: O(4 x N x M) ~ O(N x M)
 * SC: O(2 x N x M) ~ O(N x M)
 * 
 */
public class P4_Shortest_Path_In_Binary_Maze {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 1, 1, 1 },
                { 0, 1, 1, 0 },
                { 0, 0, 1, 1 }
        };
        Point src = new Point(0, 0);
        Point dest = new Point(2, 3);
        int shortestPath = shortestPathBinaryMatrix(matrix, src, dest);
        System.out.println("Shortest path from (0, 0) to (2, 3) in Graph matrix is : " + shortestPath);
    }

    public static int shortestPathBinaryMatrix(int[][] matrix, Point src, Point dest) {
        if (src.x == dest.x && src.y == dest.y) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] distance = new int[rows][cols];
        Queue<Pair> queue = new LinkedList<Pair>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = (int) 1e9;
            }
        }
        distance[src.x][src.y] = 0;
        queue.offer(new Pair(0, src.x, src.y));

        int[] deltaRow = { -1, 0, 1, 0 };
        int[] deltaCol = { 0, 1, 0, -1 };
        int directions = 4;
        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            int currentDistance = currentPair.distance;

            for (int i = 0; i < directions; i++) {
                int traversedRow = currentPair.row + deltaRow[i];
                int traversedCol = currentPair.col + deltaCol[i];

                if (traversedRow >= 0 && traversedRow < rows &&
                        traversedCol >= 0 && traversedCol < cols &&
                        matrix[traversedRow][traversedCol] == 1 &&
                        currentDistance + 1 < distance[traversedRow][traversedCol]) {
                    distance[traversedRow][traversedCol] = currentDistance + 1;
                    if (traversedRow == dest.x && traversedCol == dest.y) {
                        return distance[traversedRow][traversedCol];
                    }
                    queue.offer(new Pair(distance[traversedRow][traversedCol], traversedRow, traversedCol));
                }
            }
        }
        return -1;
    }

    static class Pair {
        int distance;
        int row;
        int col;

        public Pair(int distance, int row, int col) {
            this.distance = distance;
            this.row = row;
            this.col = col;
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
