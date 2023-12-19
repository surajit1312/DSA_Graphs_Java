package traversals;

import java.util.Arrays;

/**
 * 
 * CodingNinjas: Replace O's with X's
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/replace-%E2%80%98o%E2%80%99-with-%E2%80%98x%E2%80%99_630517
 * https://www.geeksforgeeks.org/problems/replace-os-with-xs0052/1
 * 
 * TC: O(4 x n x m) + O(2n) + O(2m) ~ O(n x m)
 * SC: O(2 x n x m) + O(n x m) = O(3 x n x m) ~ O(n x m)
 * 
 */
public class P5_Replace_Os_With_Xs {
    public static void main(String[] args) {
        int n = 5;
        int m = 4;
        char[][] mat = {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'X', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'X', 'X' },
                { 'X', 'X', 'O', 'O' }
        };
        char[][] replacedMat = fill(n, m, mat);
        System.out.println("Matrix after replacing 0's with X's looks like : " + Arrays.deepToString(replacedMat));
    }

    private static char[][] fill(int n, int m, char a[][]) {
        int[][] visited = new int[n][m];
        // check for boundary zero's
        // direction - top - (left to right) - row = 0, col is looped
        for (int i = 0; i < m; i++) {
            if (a[0][i] == 'O' && visited[0][i] == 0) {
                dfsGraphHelper(a, 0, i, visited, n, m);
            }
        }
        // direction - right - (top to bottom) - col = m - 1, row is looped
        for (int i = 0; i < n; i++) {
            if (a[i][m - 1] == 'O' && visited[i][m - 1] == 0) {
                dfsGraphHelper(a, i, m - 1, visited, n, m);
            }
        }
        // direction - bottom - (right to left) - row = n - 1, col is looped
        for (int i = 0; i < m; i++) {
            if (a[n - 1][i] == 'O' && visited[n - 1][i] == 0) {
                dfsGraphHelper(a, n - 1, i, visited, n, m);
            }
        }
        // direction - left - (top to bottom) - col = 0, row is looped
        for (int i = 0; i < n; i++) {
            if (a[i][0] == 'O' && visited[i][0] == 0) {
                dfsGraphHelper(a, i, 0, visited, n, m);
            }
        }
        char[][] replacedMat = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                replacedMat[i][j] = visited[i][j] == 1 ? 'O' : 'X';
            }
        }
        return replacedMat;
    }

    private static void dfsGraphHelper(char[][] a, int row, int col, int[][] visited, int n, int m) {
        if (visited[row][col] != 0) {
            return;
        }
        visited[row][col] = 1;
        int directions = 4;
        int[] deltaRow = { -1, 0, 1, 0 };
        int[] deltaCol = { 0, 1, 0, -1 };
        for (int i = 0; i < directions; i++) {
            int visitedRow = row + deltaRow[i];
            int visitedCol = col + deltaCol[i];
            if (visitedRow >= 0 && visitedRow < n && visitedCol >= 0 && visitedCol < m
                    && visited[visitedRow][visitedCol] == 0 && a[visitedRow][visitedCol] == 'O') {
                a[visitedRow][visitedCol] = 'X';
                dfsGraphHelper(a, visitedRow, visitedCol, visited, n, m);
            }
        }
    }
}
