class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if (k < Math.abs(x - r) + Math.abs(y - c)) {
            return "impossible";
        }

        int spareK = k - Math.abs(x - r) - Math.abs(y - c);

        if (spareK % 2 == 1) {
            return "impossible";
        }

        StringBuilder answer = new StringBuilder();

        if (r - x > 0) {
            answer.append("d".repeat(r - x));

            x = r;
        }

        if (x < n) {
            int requisiteK = Math.min(spareK / 2, n - x);

            answer.append("d".repeat(requisiteK));

            x += requisiteK;
            spareK -= requisiteK * 2;
        }

        if (y - c > 0) {
            answer.append("l".repeat(y - c));

            y = c;
        }

        if (y > 1) {
            int requisiteK = Math.min(spareK / 2, y - 1);

            answer.append("l".repeat(requisiteK));

            y -= requisiteK;
            spareK -= requisiteK * 2;
        }

        answer.append("rl".repeat(spareK / 2));

        if (c - y > 0) {
            answer.append("r".repeat(c - y));
        }

        if (x - r > 0) {
            answer.append("u".repeat(x - r));
        }

        return answer.toString();
    }
}