import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        String number = in.readLine();

        StringBuilder output = new StringBuilder();

        for (int digit = 0; digit < N; digit++) {
            int count = 0;
            boolean deletion = false;

            for (int nextDigit = digit + 1; nextDigit < Math.min(N, digit + K + 1); nextDigit++) {
                count++;

                if (number.charAt(digit) < number.charAt(nextDigit)) {
                    K -= count;
                    digit += count - 1;
                    deletion = true;

                    break;
                }
            }

            if (!deletion) {
                output.append(number.charAt(digit));
            }
        }

        if (K > 0) {
            output.delete(output.length() - K, output.length());
        }

        out.write(output.toString());

        in.close();
        out.flush();
        out.close();
    }
}