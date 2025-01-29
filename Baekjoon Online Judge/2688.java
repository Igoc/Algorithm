import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        long[] nonDecreasingNumberCounts = new long[65];
        long[] digitCounts = new long[10];

        nonDecreasingNumberCounts[1] = 10L;
        Arrays.fill(digitCounts, 1L);

        for (int length = 2; length <= 64; length++) {
            long count = 1L;

            for (int digit = 8; digit >= 0; digit--) {
                digitCounts[digit] += digitCounts[digit + 1];
                count += digitCounts[digit];
            }

            nonDecreasingNumberCounts[length] = count;
        }

        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(in.readLine());

            out.write(nonDecreasingNumberCounts[n] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}