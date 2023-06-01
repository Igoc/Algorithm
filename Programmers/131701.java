import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> continuousPartialSequenceSum = new HashSet<>();

        for (int length = 1; length <= elements.length; length++) {
            int sum = 0;

            for (int index = 0; index < length; index++) {
                sum += elements[index];
            }

            continuousPartialSequenceSum.add(sum);

            for (int index = length; index < elements.length + length; index++) {
                sum -= elements[(index - length) % elements.length];
                sum += elements[index % elements.length];

                continuousPartialSequenceSum.add(sum);
            }
        }

        return continuousPartialSequenceSum.size();
    }
}