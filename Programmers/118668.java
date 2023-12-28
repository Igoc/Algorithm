import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int targetAlgorithmPower = alp;
        int targetCodingPower = cop;

        for (int[] problem : problems) {
            targetAlgorithmPower = Math.max(targetAlgorithmPower, problem[0]);
            targetCodingPower = Math.max(targetCodingPower, problem[1]);
        }

        int[][] costs = new int[targetAlgorithmPower + 1][targetCodingPower + 1];

        Arrays.stream(costs)
                .forEach(row -> Arrays.fill(row, 1000000000));

        for (int algorithmPower = 0; algorithmPower <= targetAlgorithmPower; algorithmPower++) {
            for (int codingPower = 0; codingPower <= targetCodingPower; codingPower++) {
                if (algorithmPower <= alp && codingPower <= cop) {
                    costs[algorithmPower][codingPower] = 0;
                }

                if (algorithmPower < targetAlgorithmPower) {
                    costs[algorithmPower + 1][codingPower] = Math.min(
                            costs[algorithmPower + 1][codingPower],
                            costs[algorithmPower][codingPower] + 1
                    );
                }

                if (codingPower < targetCodingPower) {
                    costs[algorithmPower][codingPower + 1] = Math.min(
                            costs[algorithmPower][codingPower + 1],
                            costs[algorithmPower][codingPower] + 1
                    );
                }

                for (int[] problem : problems) {
                    if (problem[0] > algorithmPower || problem[1] > codingPower) {
                        continue;
                    }

                    int newAlgorithmPower = Math.min(algorithmPower + problem[2], targetAlgorithmPower);
                    int newCodingPower = Math.min(codingPower + problem[3], targetCodingPower);

                    costs[newAlgorithmPower][newCodingPower] = Math.min(
                            costs[newAlgorithmPower][newCodingPower],
                            costs[algorithmPower][codingPower] + problem[4]
                    );
                }
            }
        }

        return costs[targetAlgorithmPower][targetCodingPower];
    }
}