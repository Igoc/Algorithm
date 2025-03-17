import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(in.readLine());

        List<String> minimumNumbers = findMinimumNumbers();
        List<String> maximumNumbers = findMaximumNumbers();

        while (testCase-- > 0) {
            int n = Integer.parseInt(in.readLine());

            out.write(minimumNumbers.get(n) + ' ' + maximumNumbers.get(n) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }

    public static List<String> findMinimumNumbers() {
        List<String> result = new ArrayList<>(List.of("-1", "-1", "1", "7", "4", "2", "6", "8"));

        for (int matchstick = 8; matchstick <= 100; matchstick++) {
            long minimumNumber = Long.MAX_VALUE;

            for (int left = 2; left <= matchstick / 2; left++) {
                int right = matchstick - left;

                minimumNumber = Math.min(minimumNumber, combineNumber(result.get(left), result.get(right)));
                minimumNumber = Math.min(minimumNumber, combineNumber(result.get(right), result.get(left)));
            }

            result.add(String.valueOf(minimumNumber));
        }

        return result;
    }

    public static long combineNumber(String left, String right) {
        if (right.charAt(0) == '6') {
            right = "0" + right.substring(1);
        }

        return Long.parseLong(left + right);
    }

    public static List<String> findMaximumNumbers() {
        List<String> result = new ArrayList<>(List.of("-1", "-1"));

        for (int matchstick = 2; matchstick <= 100; matchstick++) {
            if (matchstick % 2 == 0) {
                result.add("1".repeat(matchstick / 2));
            } else {
                result.add("7" + "1".repeat((matchstick - 3) / 2));
            }
        }

        return result;
    }
}