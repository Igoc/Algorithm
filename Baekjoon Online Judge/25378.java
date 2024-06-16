import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] pebbles = new int[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        for (int index = 1; index <= N; index++) {
            pebbles[index] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] counts = new int[N + 1];

        for (int origin = 1; origin <= N; origin++) {
            int pebbleNumber = pebbles[origin];

            counts[origin] = Math.max(counts[origin], counts[origin - 1]);

            for (int current = origin + 1; current <= N; current++) {
                pebbleNumber = pebbles[current] - pebbleNumber;

                if (pebbleNumber < 0) {
                    break;
                }

                if (pebbleNumber == 0) {
                    counts[current] = Math.max(counts[current], counts[origin - 1] + 1);

                    break;
                }
            }
        }

        out.write(String.valueOf(N - counts[N]));

        in.close();
        out.flush();
        out.close();
    }
}