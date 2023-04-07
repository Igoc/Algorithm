import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int calculateDecreasingNumber(StringBuilder decreasingNumber, int maxDigit, int sequence, int N) {
        if (decreasingNumber.length() == 10) {
            return sequence + 1;
        }

        for (int digit = 0; digit <= maxDigit; digit++) {
            sequence = calculateDecreasingNumber(
                    decreasingNumber.append(digit),
                    (digit == 0 && maxDigit == 9) ? (maxDigit) : (digit - 1),
                    sequence,
                    N
            );

            if (sequence == N) {
                return sequence;
            }

            decreasingNumber.setLength(decreasingNumber.length() - 1);
        }

        if (decreasingNumber.length() == 0) {
            return -1;
        }

        return sequence;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        StringBuilder decreasingNumber = new StringBuilder();

        int sequence = calculateDecreasingNumber(decreasingNumber, 9, -1, N);

        out.write((sequence == -1) ? ("-1") : (String.valueOf(Long.parseLong(decreasingNumber.toString()))));

        in.close();
        out.flush();
        out.close();
    }
}