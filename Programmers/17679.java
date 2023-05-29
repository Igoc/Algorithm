class Solution {
    public boolean checkErasingCondition(int x, int y, char[][] board) {
        return board[y][x] != '.' && board[y][x + 1] == board[y][x] && board[y + 1][x] == board[y][x] && board[y + 1][x + 1] == board[y][x];
    }

    public int eraseBlock(int x, int y, char[][] board, boolean[][] erasedBlock) {
        if (erasedBlock[y][x]) {
            return 0;
        }

        for (int row = y; row > 0; row--) {
            board[row][x] = board[row - 1][x];
        }

        board[0][x] = '.';
        erasedBlock[y][x] = true;

        return 1;
    }

    public int solution(int m, int n, String[] board) {
        char[][] currentBoard = new char[m][n];
        int answer = 0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                currentBoard[row][col] = board[row].charAt(col);
            }
        }

        while (true) {
            char[][] nextBoard = new char[m][n];
            boolean[][] erasedBlock = new boolean[m][n];
            int score = 0;

            for (int row = 0; row < m; row++) {
                nextBoard[row] = currentBoard[row].clone();
            }

            for (int row = 0; row < m - 1; row++) {
                for (int col = 0; col < n - 1; col++) {
                    if (checkErasingCondition(col, row, currentBoard)) {
                        score += eraseBlock(col, row, nextBoard, erasedBlock);
                        score += eraseBlock(col + 1, row, nextBoard, erasedBlock);
                        score += eraseBlock(col, row + 1, nextBoard, erasedBlock);
                        score += eraseBlock(col + 1, row + 1, nextBoard, erasedBlock);
                    }
                }
            }

            if (score == 0) {
                break;
            }

            currentBoard = nextBoard;
            answer += score;
        }

        return answer;
    }
}