class Solution {
    public int solution(int[][] board) {
        int answer = board[0][0];

        for (int row = 1; row < board.length; row++) {
            for (int col = 1; col < board[row].length; col++) {
                if (board[row][col] == 1) {
                    board[row][col] = Math.min(Math.min(board[row - 1][col], board[row][col - 1]), board[row - 1][col - 1]) + 1;
                    answer = Math.max(board[row][col] * board[row][col], answer);
                }
            }
        }

        return answer;
    }
}