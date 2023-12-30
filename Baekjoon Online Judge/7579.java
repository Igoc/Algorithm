import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] m = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] c = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalCost = Arrays.stream(c)
                .sum();

        int[][] table = new int[totalCost + 1][N + 1];

        loop:
        for (int cost = 0; cost < table.length; cost++) {
            for (int app = 1; app < table[cost].length; app++) {
                table[cost][app] = table[cost][app - 1];

                if (cost >= c[app - 1]) {
                    table[cost][app] = Math.max(table[cost][app], table[cost - c[app - 1]][app - 1] + m[app - 1]);
                }

                if (table[cost][app] >= M) {
                    out.write(String.valueOf(cost));

                    break loop;
                }
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}