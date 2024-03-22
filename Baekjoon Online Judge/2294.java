import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[] coins = new int[n];

        for (int index = 0; index < n; index++) {
            coins[index] = Integer.parseInt(in.readLine());
        }

        int[] values = new int[k + 1];

        Arrays.fill(values, Integer.MAX_VALUE);
        values[0] = 0;

        for (int value = 0; value < k; value++) {
            if (values[value] == Integer.MAX_VALUE) {
                continue;
            }

            for (int coin : coins) {
                if (value + coin <= k && values[value] + 1 < values[value + coin]) {
                    values[value + coin] = values[value] + 1;
                }
            }
        }

        out.write(String.valueOf((values[k] == Integer.MAX_VALUE) ? (-1) : (values[k])));

        in.close();
        out.flush();
        out.close();
    }
}