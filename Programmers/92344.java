class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;

        int[][] varianceBoard = new int[N + 1][M + 1];

        for (int[] situation : skill) {
            int r1 = situation[1];
            int c1 = situation[2];
            int r2 = situation[3] + 1;
            int c2 = situation[4] + 1;
            int degree = (situation[0] == 1) ? (-situation[5]) : (situation[5]);

            varianceBoard[r1][c1] += degree;
            varianceBoard[r1][c2] -= degree;
            varianceBoard[r2][c1] -= degree;
            varianceBoard[r2][c2] += degree;
        }

        int answer = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 1; col < M; col++) {
                varianceBoard[row][col] += varianceBoard[row][col - 1];
            }
        }

        for (int col = 0; col < M; col++) {
            if (board[0][col] + varianceBoard[0][col] > 0) {
                answer++;
            }

            for (int row = 1; row < N; row++) {
                varianceBoard[row][col] += varianceBoard[row - 1][col];

                if (board[row][col] + varianceBoard[row][col] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}