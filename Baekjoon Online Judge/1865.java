import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Edge {
    public int start;
    public int end;
    public int time;

    public Edge(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static boolean findNegativeCycle(int vertexNumber, List<Edge> edges) {
        int[] distances = new int[vertexNumber + 1];

        edges = new ArrayList<>(edges);

        for (int vertex = 0; vertex < vertexNumber; vertex++) {
            edges.add(new Edge(vertexNumber, vertex, 0));
            distances[vertex] = Integer.MAX_VALUE;
        }

        for (int iteration = 0; iteration < vertexNumber; iteration++) {
            for (Edge edge : edges) {
                if (distances[edge.start] != Integer.MAX_VALUE && distances[edge.start] + edge.time < distances[edge.end]) {
                    distances[edge.end] = distances[edge.start] + edge.time;
                }
            }
        }

        for (Edge edge : edges) {
            if (distances[edge.start] != Integer.MAX_VALUE && distances[edge.start] + edge.time < distances[edge.end]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < TC; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int W = Integer.parseInt(tokenizer.nextToken());

            int[][] distances = new int[N][N];

            Arrays.stream(distances)
                    .forEach(distance -> Arrays.fill(distance, Integer.MAX_VALUE));

            for (int path = 0; path < M; path++) {
                tokenizer = new StringTokenizer(in.readLine());

                int S = Integer.parseInt(tokenizer.nextToken()) - 1;
                int E = Integer.parseInt(tokenizer.nextToken()) - 1;
                int T = Integer.parseInt(tokenizer.nextToken());

                distances[S][E] = Math.min(distances[S][E], T);
                distances[E][S] = Math.min(distances[E][S], T);
            }

            for (int wormhole = 0; wormhole < W; wormhole++) {
                tokenizer = new StringTokenizer(in.readLine());

                int S = Integer.parseInt(tokenizer.nextToken()) - 1;
                int E = Integer.parseInt(tokenizer.nextToken()) - 1;
                int T = Integer.parseInt(tokenizer.nextToken());

                distances[S][E] = Math.min(distances[S][E], -T);
            }

            List<Edge> edges = new ArrayList<>();

            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (distances[start][end] != Integer.MAX_VALUE) {
                        edges.add(new Edge(start, end, distances[start][end]));
                    }
                }
            }

            out.write((findNegativeCycle(N, edges)) ? ("YES\n") : ("NO\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}