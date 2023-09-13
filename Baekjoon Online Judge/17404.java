import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] houses = new int[N][3];

        for (int[] house : houses) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            house[0] = Integer.parseInt(tokenizer.nextToken());
            house[1] = Integer.parseInt(tokenizer.nextToken());
            house[2] = Integer.parseInt(tokenizer.nextToken());
        }

        int output = Integer.MAX_VALUE;

        for (int color = 0; color < 3; color++) {
            int[][] costs = new int[N][3];

            costs[0][color] = houses[0][color];
            costs[0][(color + 1) % 3] = 1000000;
            costs[0][(color + 2) % 3] = 1000000;

            for (int index = 1; index < N; index++) {
                costs[index][0] += Math.min(costs[index - 1][1], costs[index - 1][2]) + houses[index][0];
                costs[index][1] += Math.min(costs[index - 1][0], costs[index - 1][2]) + houses[index][1];
                costs[index][2] += Math.min(costs[index - 1][0], costs[index - 1][1]) + houses[index][2];
            }

            output = Math.min(output, Math.min(costs[N - 1][(color + 1) % 3], costs[N - 1][(color + 2) % 3]));
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}