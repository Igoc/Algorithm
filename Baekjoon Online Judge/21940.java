import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_COST = 200000;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] costs = new int[N + 1][N + 1];

        Arrays.stream(costs)
                .forEach(cost -> Arrays.fill(cost, MAX_COST + 1));

        for (int city = 1; city <= N; city++) {
            costs[city][city] = 0;
        }

        for (int path = 1; path <= M; path++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int T = Integer.parseInt(tokenizer.nextToken());

            if (T < costs[A][B]) {
                costs[A][B] = T;
            }
        }

        int K = Integer.parseInt(in.readLine());

        int[] C = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int waypoint = 1; waypoint <= N; waypoint++) {
            for (int origin = 1; origin <= N; origin++) {
                for (int destination = 1; destination <= N; destination++) {
                    if (costs[origin][waypoint] + costs[waypoint][destination] < costs[origin][destination]) {
                        costs[origin][destination] = costs[origin][waypoint] + costs[waypoint][destination];
                    }
                }
            }
        }

        List<Integer> output = new ArrayList<>();
        int maximumCost = Integer.MAX_VALUE;

        loop:
        for (int X = 1; X <= N; X++) {
            int currentMaximumCost = 0;

            for (int city : C) {
                if (costs[city][X] == MAX_COST + 1 || costs[X][city] == MAX_COST + 1) {
                    continue loop;
                }

                currentMaximumCost = Math.max(costs[city][X] + costs[X][city], currentMaximumCost);
            }

            if (currentMaximumCost == maximumCost) {
                output.add(X);
            } else if (currentMaximumCost < maximumCost) {
                output.clear();
                output.add(X);
                maximumCost = currentMaximumCost;
            }
        }

        for (int city : output) {
            out.write(city + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}