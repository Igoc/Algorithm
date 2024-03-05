import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());

        int[][] costs = new int[n][n];

        Arrays.stream(costs)
                .forEach(cost -> Arrays.fill(cost, Integer.MAX_VALUE));

        for (int city = 0; city < n; city++) {
            costs[city][city] = 0;
        }

        for (int bus = 0; bus < m; bus++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken());

            costs[a][b] = Math.min(costs[a][b], c);
        }

        for (int waypoint = 0; waypoint < n; waypoint++) {
            for (int source = 0; source < n; source++) {
                for (int destination = 0; destination < n; destination++) {
                    if (costs[source][waypoint] != Integer.MAX_VALUE && costs[waypoint][destination] != Integer.MAX_VALUE && costs[source][waypoint] + costs[waypoint][destination] < costs[source][destination]) {
                        costs[source][destination] = costs[source][waypoint] + costs[waypoint][destination];
                    }
                }
            }
        }

        for (int source = 0; source < n; source++) {
            for (int destination = 0; destination < n; destination++) {
                if (costs[source][destination] == Integer.MAX_VALUE) {
                    costs[source][destination] = 0;
                }

                out.write(costs[source][destination] + " ");
            }

            out.write("\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}