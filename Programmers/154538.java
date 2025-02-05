import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) {
            return 0;
        }

        int[] counts = new int[y + 1];

        Arrays.fill(counts, Integer.MAX_VALUE);
        counts[x] = 0;

        for (int number = x; number < y; number++) {
            if (counts[number] == Integer.MAX_VALUE) {
                continue;
            }

            if (number + n <= y) {
                counts[number + n] = Math.min(counts[number + n], counts[number] + 1);
            }

            if (number * 2 <= y) {
                counts[number * 2] = Math.min(counts[number * 2], counts[number] + 1);
            }

            if (number * 3 <= y) {
                counts[number * 3] = Math.min(counts[number * 3], counts[number] + 1);
            }
        }

        return (counts[y] == Integer.MAX_VALUE) ? (-1) : (counts[y]);
    }
}