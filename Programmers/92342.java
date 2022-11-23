class Output {
    public int scoreDifference;
    public int[] target;

    public Output(int scoreDifference, int[] target) {
        this.scoreDifference = scoreDifference;
        this.target = target;
    }
}

class Solution {
    public static final int TARGET_MAX_SCORE = 10;

    public Output calculateOptimalSolution(int apeachScore, int[] apeachTarget, int ryanScore, int[] ryanTarget, int targetScore, int remainingArrowNumber, Output output) {
        if (targetScore == 0 || remainingArrowNumber == 0) {
            int newScoreDifference = ryanScore - apeachScore;
            int[] newTarget = ryanTarget.clone();

            newTarget[TARGET_MAX_SCORE] = remainingArrowNumber;

            if (newScoreDifference > output.scoreDifference) {
                return new Output(newScoreDifference, newTarget);
            } else if (newScoreDifference > 0 && newScoreDifference == output.scoreDifference) {
                for (int index = TARGET_MAX_SCORE; index >= 0; index--) {
                    if (newTarget[index] > output.target[index]) {
                        return new Output(newScoreDifference, newTarget);
                    } else if (newTarget[index] < output.target[index]) {
                        return output;
                    }
                }
            }

            return output;
        }

        int index = TARGET_MAX_SCORE - targetScore;

        if (remainingArrowNumber > apeachTarget[index]) {
            ryanTarget[index] = apeachTarget[index] + 1;
            output = calculateOptimalSolution((apeachTarget[index] == 0) ? (apeachScore) : (apeachScore - targetScore), apeachTarget, ryanScore + targetScore, ryanTarget, targetScore - 1, remainingArrowNumber - apeachTarget[index] - 1, output);
            ryanTarget[index] = 0;
        }

        output = calculateOptimalSolution(apeachScore, apeachTarget, ryanScore, ryanTarget, targetScore - 1, remainingArrowNumber, output);

        return output;
    }

    public int[] solution(int n, int[] info) {
        int apeachScore = 0;

        for (int index = 0; index < TARGET_MAX_SCORE; index++) {
            if (info[index] > 0) {
                apeachScore += TARGET_MAX_SCORE - index;
            }
        }

        int[] answer = calculateOptimalSolution(apeachScore, info, 0, new int[TARGET_MAX_SCORE + 1], TARGET_MAX_SCORE, n, new Output(0, new int[]{-1})).target;

        return answer;
    }
}