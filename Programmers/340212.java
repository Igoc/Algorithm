class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int minLevel = 1;
        int maxLevel = 100000;

        while (minLevel <= maxLevel) {
            long level = (minLevel + maxLevel) >> 1;
            long time = times[0];

            for (int index = 1; index < diffs.length; index++) {
                if (diffs[index] <= level) {
                    time += times[index];
                } else {
                    time += (diffs[index] - level) * (times[index] + times[index - 1]) + times[index];
                }
            }

            if (time > limit) {
                minLevel = (int) level + 1;
            } else {
                maxLevel = (int) level - 1;
            }
        }

        return minLevel;
    }
}