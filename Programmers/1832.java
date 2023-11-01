class Solution {
    public static final int MODULO_NUMBER = 20170805;

    public static final int LEFT_TO_RIGHT = 0;
    public static final int TOP_TO_BOTTOM = 1;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] pathNumber = new int[m][n][2];

        pathNumber[0][1][LEFT_TO_RIGHT] = 1;
        pathNumber[1][0][TOP_TO_BOTTOM] = 1;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (col - 1 >= 0) {
                    if (cityMap[row][col - 1] == 0) {
                        pathNumber[row][col][LEFT_TO_RIGHT] += pathNumber[row][col - 1][TOP_TO_BOTTOM];
                    }

                    if (cityMap[row][col - 1] != 1) {
                        pathNumber[row][col][LEFT_TO_RIGHT] += pathNumber[row][col - 1][LEFT_TO_RIGHT];
                    }
                }

                if (row - 1 >= 0) {
                    if (cityMap[row - 1][col] == 0) {
                        pathNumber[row][col][TOP_TO_BOTTOM] += pathNumber[row - 1][col][LEFT_TO_RIGHT];
                    }

                    if (cityMap[row - 1][col] != 1) {
                        pathNumber[row][col][TOP_TO_BOTTOM] += pathNumber[row - 1][col][TOP_TO_BOTTOM];
                    }
                }

                pathNumber[row][col][LEFT_TO_RIGHT] %= MODULO_NUMBER;
                pathNumber[row][col][TOP_TO_BOTTOM] %= MODULO_NUMBER;
            }
        }

        return (pathNumber[m - 1][n - 1][LEFT_TO_RIGHT] + pathNumber[m - 1][n - 1][TOP_TO_BOTTOM]) % MODULO_NUMBER;
    }
}