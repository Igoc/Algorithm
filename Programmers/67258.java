import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> gemCount = new HashMap<>();

        for (String gem : gems) {
            if (!gemCount.containsKey(gem)) {
                gemCount.put(gem, 0);
            }
        }

        int left = 0;
        int selectedTypeCount = 0;

        int[] answer = {0, Integer.MAX_VALUE};

        for (int right = 0; right < gems.length; right++) {
            gemCount.replace(gems[right], gemCount.get(gems[right]) + 1);

            if (gemCount.get(gems[right]) == 1) {
                selectedTypeCount++;
            }

            if (selectedTypeCount == gemCount.size()) {
                while (left <= right) {
                    gemCount.replace(gems[left], gemCount.get(gems[left]) - 1);
                    left++;

                    if (gemCount.get(gems[left - 1]) == 0) {
                        selectedTypeCount--;

                        break;
                    }
                }

                if (right - left + 1 < answer[1] - answer[0]) {
                    answer[0] = left;
                    answer[1] = right + 1;
                }
            }
        }

        return answer;
    }
}