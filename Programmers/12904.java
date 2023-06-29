class Solution {
    public int solution(String s) {
        int answer = 1;

        for (int middle = 1; middle < s.length() - 1; middle++) {
            int left = middle - 1;
            int right = middle + 1;
            int length = 1;

            while (left >= 0 && s.charAt(left) == s.charAt(middle)) {
                left--;
                length++;
            }

            while (right < s.length() && s.charAt(right) == s.charAt(middle)) {
                right++;
                length++;
            }

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                length += 2;
            }

            answer = Math.max(answer, length);
        }

        return answer;
    }
}