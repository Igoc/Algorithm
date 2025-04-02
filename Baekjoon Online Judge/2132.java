import java.io.*;
import java.util.*;

class Path {
    public int vertex;
    public int fruitNumber;

    public Path(int vertex, int fruitNumber) {
        this.vertex = vertex;
        this.fruitNumber = fruitNumber;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[] fruitNumbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<List<Integer>> edges = new ArrayList<>();

        for (int vertex = 0; vertex < n; vertex++) {
            edges.add(new ArrayList<>());
        }

        for (int edge = 0; edge < n - 1; edge++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;

            edges.get(A).add(B);
            edges.get(B).add(A);
        }

        Path start = findPathWithMaximumFruitNumber(fruitNumbers, edges, 0);
        Path end = findPathWithMaximumFruitNumber(fruitNumbers, edges, start.vertex);

        out.write(end.fruitNumber + " " + (Math.min(start.vertex, end.vertex) + 1));

        in.close();
        out.flush();
        out.close();
    }

    public static Path findPathWithMaximumFruitNumber(int[] fruitNumbers, List<List<Integer>> edges, int origin) {
        Deque<Path> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[fruitNumbers.length];
        Path result = new Path(0, Integer.MIN_VALUE);

        deque.offer(new Path(origin, fruitNumbers[origin]));
        visited[origin] = true;

        while (!deque.isEmpty()) {
            Path path = deque.poll();

            if (path.fruitNumber > result.fruitNumber || path.fruitNumber == result.fruitNumber && path.vertex < result.vertex) {
                result = path;
            }

            for (int vertex : edges.get(path.vertex)) {
                if (!visited[vertex]) {
                    deque.offer(new Path(vertex, path.fruitNumber + fruitNumbers[vertex]));
                    visited[vertex] = true;
                }
            }
        }

        return result;
    }
}