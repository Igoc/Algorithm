import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.<int[]>comparingInt(o -> o[1]).thenComparing(Comparator.<int[]>comparingInt(o -> o[0]).reversed()));

        int[] base = targets[0];
        int answer = 1;

        for (int index = 1; index < targets.length; index++) {
            if (targets[index][0] < base[1] && targets[index][1] > base[0]) {
                continue;
            }

            base = targets[index];
            answer++;
        }

        return answer;
    }
}