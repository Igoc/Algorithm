import java.io.*;
import java.util.*;

class Path {
    public int city;
    public int time;

    public Path(int city, int time) {
        this.city = city;
        this.time = time;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<List<Path>> paths = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            paths.add(new ArrayList<>());
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            int t = Integer.parseInt(tokenizer.nextToken());

            paths.get(a).add(new Path(b, t));
            paths.get(b).add(new Path(a, t));
        }

        int[] originalTimes = new int[N];
        List<List<Integer>> routes = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            originalTimes[index] = Integer.MAX_VALUE;
            routes.add(new ArrayList<>());
        }

        routes.get(0).add(0);

        calculateMinimumTimes(originalTimes, routes, paths);

        List<Integer> route = routes.get(N - 1);
        int output = 0;

        for (int index = 1; index < route.size(); index++) {
            int[] times = new int[N];

            Arrays.fill(times, Integer.MAX_VALUE);

            calculateMinimumTimes(route.get(index - 1), route.get(index), times, paths);

            if (times[N - 1] == Integer.MAX_VALUE) {
                output = -1;

                break;
            }

            output = Math.max(output, times[N - 1] - originalTimes[N - 1]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static void calculateMinimumTimes(int[] times, List<List<Integer>> routes, List<List<Path>> paths) {
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

        priorityQueue.offer(new Path(0, 0));
        times[0] = 0;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (times[path.city] < path.time) {
                continue;
            }

            for (Path nextPath : paths.get(path.city)) {
                if (path.time + nextPath.time < times[nextPath.city]) {
                    List<Integer> route = routes.get(nextPath.city);

                    times[nextPath.city] = path.time + nextPath.time;
                    route.clear();
                    route.addAll(routes.get(path.city));
                    route.add(nextPath.city);
                    priorityQueue.offer(new Path(nextPath.city, times[nextPath.city]));
                }
            }
        }
    }

    public static void calculateMinimumTimes(int blockStart, int blockEnd, int[] times, List<List<Path>> paths) {
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

        priorityQueue.offer(new Path(0, 0));
        times[0] = 0;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (times[path.city] < path.time) {
                continue;
            }

            for (Path nextPath : paths.get(path.city)) {
                if (path.city == blockStart && nextPath.city == blockEnd || path.city == blockEnd && nextPath.city == blockStart) {
                    continue;
                }

                if (path.time + nextPath.time < times[nextPath.city]) {
                    times[nextPath.city] = path.time + nextPath.time;
                    priorityQueue.offer(new Path(nextPath.city, times[nextPath.city]));
                }
            }
        }
    }
}