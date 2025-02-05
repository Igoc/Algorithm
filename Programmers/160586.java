import java.util.Arrays;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] alphabets = new int[26];

        Arrays.fill(alphabets, Integer.MAX_VALUE);

        for (String key : keymap) {
            for (int sequence = 0; sequence < key.length(); sequence++) {
                alphabets[key.charAt(sequence) - 'A'] = Math.min(alphabets[key.charAt(sequence) - 'A'], sequence + 1);
            }
        }

        int[] answer = new int[targets.length];

        for (int index = 0; index < targets.length; index++) {
            for (char alphabet : targets[index].toCharArray()) {
                if (alphabets[alphabet - 'A'] == Integer.MAX_VALUE) {
                    answer[index] = -1;
                    break;
                }

                answer[index] += alphabets[alphabet - 'A'];
            }
        }

        return answer;
    }
}