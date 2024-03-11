import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        int[][] costs = new int[N][N];

        Arrays.stream(costs)
                .forEach(cost -> Arrays.fill(cost, Integer.MAX_VALUE));

        for (int person = 0; person < N; person++) {
            costs[person][person] = 0;
        }

        for (int index = 0; index < M; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;

            costs[x][y] = 1;
            costs[y][x] = 1;
        }

        for (int waypoint = 0; waypoint < N; waypoint++) {
            for (int origin = 0; origin < N; origin++) {
                for (int destination = 0; destination < N; destination++) {
                    if (costs[origin][waypoint] != Integer.MAX_VALUE && costs[waypoint][destination] != Integer.MAX_VALUE && costs[origin][waypoint] + costs[waypoint][destination] < costs[origin][destination]) {
                        costs[origin][destination] = costs[origin][waypoint] + costs[waypoint][destination];
                    }
                }
            }
        }

        List<Integer> representatives = new ArrayList<>();
        boolean[] visits = new boolean[N];

        for (int origin = 0; origin < N; origin++) {
            if (visits[origin]) {
                continue;
            }

            List<Integer> committee = new ArrayList<>();

            for (int destination = 0; destination < N; destination++) {
                if (costs[origin][destination] != Integer.MAX_VALUE) {
                    committee.add(destination);
                }
            }

            for (int person : committee) {
                visits[person] = true;
            }

            int representative = -1;
            int minimumCost = Integer.MAX_VALUE;

            for (int committeeOrigin : committee) {
                int cost = 0;

                for (int committeeDestination : committee) {
                    cost = Math.max(cost, costs[committeeOrigin][committeeDestination]);
                }

                if (cost < minimumCost) {
                    representative = committeeOrigin;
                    minimumCost = cost;
                }
            }

            representatives.add(representative + 1);
        }

        representatives.sort(Comparator.naturalOrder());

        out.write(representatives.size() + "\n");

        for (int representative : representatives) {
            out.write(representative + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}