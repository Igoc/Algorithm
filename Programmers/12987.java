import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] A, int[] B) {
        List<Integer> bNumbers = Arrays.stream(B)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        int answer = 0;

        for (int aNumber : A) {
            int minimumIndex = 0;
            int maximumIndex = bNumbers.size() - 1;

            while (minimumIndex <= maximumIndex) {
                int index = (minimumIndex + maximumIndex) / 2;

                if (bNumbers.get(index) <= aNumber) {
                    minimumIndex = index + 1;
                } else {
                    maximumIndex = index - 1;
                }
            }

            minimumIndex = Math.min(minimumIndex, bNumbers.size() - 1);

            if (bNumbers.get(minimumIndex) <= aNumber) {
                bNumbers.remove(0);
            } else {
                bNumbers.remove(minimumIndex);
                answer++;
            }
        }

        return answer;
    }
}