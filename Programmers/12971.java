class Solution {
    public int solution(int[] sticker) {
        if (sticker.length == 1) {
            return sticker[0];
        }

        int[][] sum = new int[2][sticker.length];
        int answer = Math.max(sticker[0], sticker[1]);

        sum[0][0] = sticker[0];
        sum[1][1] = sticker[1];

        for (int index = 0; index < sticker.length; index++) {
            for (int startSticker = 0; startSticker <= 1; startSticker++) {
                if (index + 2 < sticker.length + startSticker - 1) {
                    sum[startSticker][index + 2] = Math.max(sum[startSticker][index + 2], sum[startSticker][index] + sticker[index + 2]);
                    answer = Math.max(answer, sum[startSticker][index + 2]);
                }

                if (index + 3 < sticker.length + startSticker - 1) {
                    sum[startSticker][index + 3] = Math.max(sum[startSticker][index + 3], sum[startSticker][index] + sticker[index + 3]);
                    answer = Math.max(answer, sum[startSticker][index + 3]);
                }
            }
        }

        return answer;
    }
}