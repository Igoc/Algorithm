import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Path implements Comparable<Path> {
    public int vertex;
    public int distance;

    public Path(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(distance, o.distance);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void findShortestDistances(int sourceVertex, int[][] distances) {
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[distances.length];

        for (int vertex = 0; vertex < distances.length; vertex++) {
            if (distances[sourceVertex][vertex] != Integer.MAX_VALUE) {
                priorityQueue.offer(new Path(vertex, distances[sourceVertex][vertex]));
            }
        }

        distances[sourceVertex][sourceVertex] = 0;
        visit[sourceVertex] = true;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visit[path.vertex]) {
                continue;
            }

            visit[path.vertex] = true;

            for (int vertex = 0; vertex < distances.length; vertex++) {
                if (distances[path.vertex][vertex] != Integer.MAX_VALUE && distances[sourceVertex][path.vertex] + distances[path.vertex][vertex] < distances[sourceVertex][vertex]) {
                    distances[sourceVertex][vertex] = distances[sourceVertex][path.vertex] + distances[path.vertex][vertex];
                    priorityQueue.offer(new Path(vertex, distances[sourceVertex][vertex]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int X = Integer.parseInt(tokenizer.nextToken()) - 1;

        int[][] distances = new int[N][N];
        int[][] reverseDistances = new int[N][N];

        Arrays.stream(distances)
                .forEach(distance -> Arrays.fill(distance, Integer.MAX_VALUE));

        Arrays.stream(reverseDistances)
                .forEach(reverseDistance -> Arrays.fill(reverseDistance, Integer.MAX_VALUE));

        for (int edge = 0; edge < M; edge++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            int T = Integer.parseInt(tokenizer.nextToken());

            distances[A][B] = T;
            reverseDistances[B][A] = T;
        }

        findShortestDistances(X, distances);
        findShortestDistances(X, reverseDistances);

        int output = 0;

        for (int vertex = 0; vertex < N; vertex++) {
            output = Math.max(output, distances[X][vertex] + reverseDistances[X][vertex]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}