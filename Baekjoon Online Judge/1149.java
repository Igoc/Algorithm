import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] costs = new int[N][3];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            costs[index][0] = Integer.parseInt(tokenizer.nextToken());
            costs[index][1] = Integer.parseInt(tokenizer.nextToken());
            costs[index][2] = Integer.parseInt(tokenizer.nextToken());

            if (index > 0) {
                costs[index][0] += Math.min(costs[index - 1][1], costs[index - 1][2]);
                costs[index][1] += Math.min(costs[index - 1][0], costs[index - 1][2]);
                costs[index][2] += Math.min(costs[index - 1][0], costs[index - 1][1]);
            }
        }

        out.write(String.valueOf(Math.min(costs[N - 1][0], Math.min(costs[N - 1][1], costs[N - 1][2]))));

        in.close();
        out.flush();
        out.close();
    }
}