class Solution {
    public int[] findOptimalSolution(int[][] users, int[] emoticons, int[] discountRates, int[] answer, int depth) {
        if (depth == emoticons.length) {
            int emoticonPlusMemberCount = 0;
            int purchasingCostSum = 0;

            for (int userIndex = 0; userIndex < users.length; userIndex++) {
                int purchasingCost = 0;

                for (int emoticonIndex = 0; emoticonIndex < emoticons.length; emoticonIndex++) {
                    if (users[userIndex][0] <= discountRates[emoticonIndex]) {
                        purchasingCost += emoticons[emoticonIndex] * (100 - discountRates[emoticonIndex]) / 100.0;
                    }
                }

                if (users[userIndex][1] <= purchasingCost) {
                    purchasingCost = 0;
                    emoticonPlusMemberCount++;
                }

                purchasingCostSum += purchasingCost;
            }

            if (emoticonPlusMemberCount > answer[0] || emoticonPlusMemberCount == answer[0] && purchasingCostSum > answer[1]) {
                answer[0] = emoticonPlusMemberCount;
                answer[1] = purchasingCostSum;
            }

            return answer;
        }

        for (int discountRate = 10; discountRate <= 40; discountRate += 10) {
            discountRates[depth] = discountRate;
            answer = findOptimalSolution(users, emoticons, discountRates, answer, depth + 1);
        }

        return answer;
    }

    public int[] solution(int[][] users, int[] emoticons) {
        return findOptimalSolution(users, emoticons, new int[emoticons.length], new int[2], 0);
    }
}