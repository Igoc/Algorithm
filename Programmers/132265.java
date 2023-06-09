class Solution {
    public int solution(int[] topping) {
        int[][] toppingNumber = new int[2][10001];
        int[] toppingTypeCount = new int[2];

        for (int type : topping) {
            toppingNumber[1][type]++;

            if (toppingNumber[1][type] == 1) {
                toppingTypeCount[1]++;
            }
        }

        int answer = 0;

        for (int type : topping) {
            toppingNumber[0][type]++;
            toppingNumber[1][type]--;

            if (toppingNumber[0][type] == 1) {
                toppingTypeCount[0]++;
            }

            if (toppingNumber[1][type] == 0) {
                toppingTypeCount[1]--;
            }

            if (toppingTypeCount[0] == toppingTypeCount[1]) {
                answer++;
            }
        }

        return answer;
    }
}