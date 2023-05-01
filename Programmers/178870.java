class Solution {
    public int[] solution(int[] sequence, int k) {
        int startIndex = 0;
        int endIndex = 0;
        int sum = sequence[0];

        int[] answer = {0, sequence.length - 1};

        while (startIndex <= endIndex) {
            if (sum == k) {
                int length = endIndex - startIndex;
                int answerLength = answer[1] - answer[0];

                if (length < answerLength || length == answerLength && startIndex < answer[0]) {
                    answer[0] = startIndex;
                    answer[1] = endIndex;
                }
            }

            if (sum > k) {
                sum -= sequence[startIndex++];
            } else {
                if (endIndex == sequence.length - 1) {
                    break;
                }

                sum += sequence[++endIndex];
            }
        }

        return answer;
    }
}