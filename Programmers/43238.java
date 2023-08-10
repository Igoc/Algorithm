class Solution {
    public long solution(int n, int[] times) {
        long start = 0L;
        long end = 0L;

        for (long time : times) {
            end = Math.max(end, time * n);
        }

        long answer = end;

        while (start <= end) {
            long mid = (start + end) / 2;
            long throughput = 0L;

            for (long time : times) {
                throughput += mid / time;
            }

            if (throughput >= n) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }
}