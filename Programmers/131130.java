class Solution {
    public int solution(int[] cards) {
        int[] scores = {0, 0};
        boolean[] open = new boolean[cards.length];

        for (int index = 0; index < cards.length; index++) {
            if (!open[index]) {
                int boxIndex = index;
                int score = 1;

                open[boxIndex] = true;

                while (true) {
                    if (score == cards.length) {
                        return 0;
                    }

                    if (open[cards[boxIndex] - 1]) {
                        break;
                    }

                    boxIndex = cards[boxIndex] - 1;
                    open[boxIndex] = true;
                    score++;
                }

                if (score > scores[0]) {
                    scores[1] = scores[0];
                    scores[0] = score;
                } else if (score > scores[1]) {
                    scores[1] = score;
                }
            }
        }

        return scores[0] * scores[1];
    }
}