class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] matrix = new char[m][n];
        int result = 0;
        for (int i = 0; i <= guards.length - 1; i++) {
            int guardRow = guards[i][0];
            int guardCol = guards[i][1];
            matrix[guardRow][guardCol] = 'G';
        }
        for (int i = 0; i <= walls.length - 1; i++) {
            int wallRow = walls[i][0];
            int wallCol = walls[i][1];
            matrix[wallRow][wallCol] = 'W';
        }
        for (int i = 0; i <= guards.length - 1; i++) {
            int currentRow = guards[i][0];
            int currentCol = guards[i][1] - 1;
            while (currentCol >= 0) {
                if (matrix[currentRow][currentCol] != 'W'
                        && matrix[currentRow][currentCol] != 'G') {
                    matrix[currentRow][currentCol] = 'R';
                } else {
                    break;
                }
                currentCol--;
            }
            currentRow = guards[i][0];
            currentCol = guards[i][1] + 1;
            while (currentCol <= n - 1) {
                if (matrix[currentRow][currentCol] != 'W'
                        && matrix[currentRow][currentCol] != 'G') {
                    matrix[currentRow][currentCol] = 'R';
                } else {
                    break;
                }
                currentCol++;
            }
            currentRow = guards[i][0] - 1;
            currentCol = guards[i][1];
            while (currentRow >= 0) {
                if (matrix[currentRow][currentCol] != 'W'
                        && matrix[currentRow][currentCol] != 'G') {
                    matrix[currentRow][currentCol] = 'R';
                } else {
                    break;
                }
                currentRow--;
            }
            currentRow = guards[i][0] + 1;
            currentCol = guards[i][1];
            while (currentRow <= m - 1) {
                if (matrix[currentRow][currentCol] != 'W'
                        && matrix[currentRow][currentCol] != 'G') {
                    matrix[currentRow][currentCol] = 'R';
                } else {
                    break;
                }
                currentRow++;
            }
        }
        for (int i = 0; i <= m - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                if (matrix[i][j] != 'R' && matrix[i][j] != 'G' && matrix[i][j] != 'W') {
                    result++;
                }
            }
        }
        return result;
    }
}
