class Solution {
    public int solution(int[] money) {
        int[][] sum = new int[2][money.length];
        int answer = Math.max(money[0], money[1]);

        sum[0][0] = money[0];
        sum[1][1] = money[1];

        for (int index = 0; index < money.length; index++) {
            for (int startHouse = 0; startHouse <= 1; startHouse++) {
                if (index + 2 < money.length + startHouse - 1) {
                    sum[startHouse][index + 2] = Math.max(sum[startHouse][index + 2], sum[startHouse][index] + money[index + 2]);
                    answer = Math.max(answer, sum[startHouse][index + 2]);
                }

                if (index + 3 < money.length + startHouse - 1) {
                    sum[startHouse][index + 3] = Math.max(sum[startHouse][index + 3], sum[startHouse][index] + money[index + 3]);
                    answer = Math.max(answer, sum[startHouse][index + 3]);
                }
            }
        }

        return answer;
    }
}