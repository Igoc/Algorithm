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
        int E = Integer.parseInt(tokenizer.nextToken());

        int[][] distances = new int[N][N];

        Arrays.stream(distances)
                .forEach(distance -> Arrays.fill(distance, Integer.MAX_VALUE));

        for (int edge = 0; edge < E; edge++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken());

            distances[a][b] = c;
            distances[b][a] = c;
        }

        tokenizer = new StringTokenizer(in.readLine());

        int v1 = Integer.parseInt(tokenizer.nextToken()) - 1;
        int v2 = Integer.parseInt(tokenizer.nextToken()) - 1;

        findShortestDistances(0, distances);
        findShortestDistances(v1, distances);
        findShortestDistances(v2, distances);

        int output = Integer.MAX_VALUE;

        if (distances[0][v1] != Integer.MAX_VALUE && distances[v1][v2] != Integer.MAX_VALUE && distances[v2][N - 1] != Integer.MAX_VALUE) {
            output = Math.min(output, distances[0][v1] + distances[v1][v2] + distances[v2][N - 1]);
        }

        if (distances[0][v2] != Integer.MAX_VALUE && distances[v2][v1] != Integer.MAX_VALUE && distances[v1][N - 1] != Integer.MAX_VALUE) {
            output = Math.min(output, distances[0][v2] + distances[v2][v1] + distances[v1][N - 1]);
        }

        out.write(String.valueOf((output == Integer.MAX_VALUE) ? (-1) : (output)));

        in.close();
        out.flush();
        out.close();
    }
}