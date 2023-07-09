import java.io.*;
import java.util.*;

class Route implements Comparable<Route> {
    public int city;
    public int cost;

    public Route(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    @Override
    public int compareTo(Route o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static List<Integer> calculateMinimumCost(int origin, int destination, int[][] costs) {
        List<List<Integer>> paths = new ArrayList<>();

        PriorityQueue<Route> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[costs.length];

        for (int city = 0; city < costs.length; city++) {
            paths.add(new ArrayList<>());

            if (costs[origin][city] != Integer.MAX_VALUE) {
                List<Integer> path = paths.get(city);

                path.add(origin);
                path.add(city);

                priorityQueue.offer(new Route(city, costs[origin][city]));
            }
        }

        costs[origin][origin] = 0;
        visit[origin] = true;

        while (!priorityQueue.isEmpty()) {
            Route route = priorityQueue.poll();

            if (visit[route.city]) {
                continue;
            }

            visit[route.city] = true;

            for (int city = 0; city < costs.length; city++) {
                if (costs[route.city][city] != Integer.MAX_VALUE && costs[origin][route.city] + costs[route.city][city] < costs[origin][city]) {
                    costs[origin][city] = costs[origin][route.city] + costs[route.city][city];

                    paths.set(city, new ArrayList<>(paths.get(route.city)));
                    paths.get(city).add(city);

                    priorityQueue.offer(new Route(city, costs[origin][city]));
                }
            }
        }

        return paths.get(destination);
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());

        int[][] costs = new int[n][n];

        Arrays.stream(costs)
                .forEach(cost -> Arrays.fill(cost, Integer.MAX_VALUE));

        for (int bus = 0; bus < m; bus++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int origin = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destination = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());

            costs[origin][destination] = Math.min(costs[origin][destination], cost);
        }

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int origin = Integer.parseInt(tokenizer.nextToken()) - 1;
        int destination = Integer.parseInt(tokenizer.nextToken()) - 1;

        List<Integer> path = calculateMinimumCost(origin, destination, costs);

        out.write(costs[origin][destination] + "\n");
        out.write(path.size() + "\n");

        for (int city : path) {
            out.write((city + 1) + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}