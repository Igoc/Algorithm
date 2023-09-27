class Solution {
    public int solution(int[] a) {
        int[] leftMinimumBalloons = new int[a.length];
        int[] rightMinimumBalloons = new int[a.length];

        int minimumBalloon = Integer.MAX_VALUE;

        for (int index = 0; index < a.length; index++) {
            if (a[index] < minimumBalloon) {
                minimumBalloon = a[index];
            }

            leftMinimumBalloons[index] = minimumBalloon;
        }

        minimumBalloon = Integer.MAX_VALUE;

        for (int index = a.length - 1; index >= 0; index--) {
            if (a[index] < minimumBalloon) {
                minimumBalloon = a[index];
            }

            rightMinimumBalloons[index] = minimumBalloon;
        }

        int answer = 0;

        for (int index = 0; index < a.length; index++) {
            if (a[index] <= leftMinimumBalloons[index] || a[index] <= rightMinimumBalloons[index]) {
                answer++;
            }
        }

        return answer;
    }
}