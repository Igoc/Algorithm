class Solution {
    int solution(int[][] land) {
        int[][] scores = new int[land.length][4];

        for (int col = 0; col < 4; col++) {
            scores[0][col] = land[0][col];
        }

        for (int row = 1; row < scores.length; row++) {
            scores[row][0] = Math.max(Math.max(scores[row - 1][1], scores[row - 1][2]), scores[row - 1][3]) + land[row][0];
            scores[row][1] = Math.max(Math.max(scores[row - 1][0], scores[row - 1][2]), scores[row - 1][3]) + land[row][1];
            scores[row][2] = Math.max(Math.max(scores[row - 1][0], scores[row - 1][1]), scores[row - 1][3]) + land[row][2];
            scores[row][3] = Math.max(Math.max(scores[row - 1][0], scores[row - 1][1]), scores[row - 1][2]) + land[row][3];
        }

        return Math.max(Math.max(Math.max(scores[scores.length - 1][0], scores[scores.length - 1][1]), scores[scores.length - 1][2]), scores[scores.length - 1][3]);
    }
}