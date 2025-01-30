import java.io.*;
import java.util.*;

class Path implements Comparable<Path> {
    public int city;
    public int distance;

    public Path(int city, int distance) {
        this.city = city;
        this.distance = distance;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(distance, o.distance);
    }
}

public class Main {
    static final int MAX_DISTANCE = 300000;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int X = Integer.parseInt(tokenizer.nextToken());

        Map<Integer, Map<Integer, Integer>> paths = new HashMap<>();
        int[] distances = new int[N + 1];

        for (int city = 0; city <= N; city++) {
            paths.put(city, new HashMap<>());

            if (city != X) {
                distances[city] = MAX_DISTANCE + 1;
            }
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());

            paths.get(A).put(B, 1);
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(List.of(new Path(X, 0)));

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();
            Map<Integer, Integer> nextPaths = paths.get(path.city);

            for (int city : nextPaths.keySet()) {
                if (distances[path.city] + nextPaths.get(city) < distances[city]) {
                    distances[city] = distances[path.city] + nextPaths.get(city);
                    priorityQueue.offer(new Path(city, distances[city]));
                }
            }
        }

        List<Integer> cities = new ArrayList<>();

        for (int city = 0; city <= N; city++) {
            if (distances[city] == K) {
                cities.add(city);
            }
        }

        if (cities.isEmpty()) {
            out.write("-1");
        } else {
            for (int city : cities) {
                out.write(city + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}