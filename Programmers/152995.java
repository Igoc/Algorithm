import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[][] scores) {
        int[] wanhoScore = {scores[0][0], scores[0][1]};

        Arrays.sort(scores, Comparator.<int[]>comparingInt(o -> o[0]).reversed().thenComparingInt(o -> o[1]));

        List<int[]> incentiveScores = new ArrayList<>();

        incentiveScores.add(scores[0]);

        for (int index = 1; index < scores.length; index++) {
            if (scores[index][1] >= incentiveScores.get(incentiveScores.size() - 1)[1]) {
                incentiveScores.add(scores[index]);
            }
        }

        incentiveScores.sort(Comparator.<int[]>comparingInt(o -> o[0] + o[1]).reversed());

        int scoreSum = incentiveScores.get(0)[0] + incentiveScores.get(0)[1];
        int rank = 1;

        for (int index = 0; index < incentiveScores.size(); index++) {
            int[] incentiveScore = incentiveScores.get(index);

            if (incentiveScore[0] + incentiveScore[1] < scoreSum) {
                scoreSum = incentiveScore[0] + incentiveScore[1];
                rank = index + 1;
            }

            if (incentiveScore[0] == wanhoScore[0] && incentiveScore[1] == wanhoScore[1]) {
                return rank;
            }
        }

        return -1;
    }
}