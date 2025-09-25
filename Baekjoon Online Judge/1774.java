import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Path {
    public int index;
    public double cost;

    public Path(int index, double cost) {
        this.index = index;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Coord[] coords = new Coord[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int X = Integer.parseInt(tokenizer.nextToken());
            int Y = Integer.parseInt(tokenizer.nextToken());

            coords[index] = new Coord(X, Y);
        }

        double[][] costs = new double[N][N];

        for (int first = 0; first < N; first++) {
            for (int second = 0; second < N; second++) {
                Coord firstCoord = coords[first];
                Coord secondCoord = coords[second];

                costs[first][second] = costs[second][first] = Math.sqrt(Math.pow(secondCoord.x - firstCoord.x, 2) + Math.pow(secondCoord.y - firstCoord.y, 2));
            }
        }

        for (int path = 0; path < M; path++) {
            tokenizer = new StringTokenizer(in.readLine());

            int first = Integer.parseInt(tokenizer.nextToken()) - 1;
            int second = Integer.parseInt(tokenizer.nextToken()) - 1;

            costs[first][second] = costs[second][first] = 0.0;
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(o -> o.cost));
        boolean[] visited = new boolean[N];

        for (int index = 0; index < N; index++) {
            priorityQueue.offer(new Path(index, costs[0][index]));
        }

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visited[path.index]) {
                continue;
            }

            costs[0][path.index] = path.cost;
            visited[path.index] = true;

            for (int index = 0; index < N; index++) {
                if (!visited[index] && costs[path.index][index] < costs[0][index]) {
                    priorityQueue.offer(new Path(index, costs[path.index][index]));
                }
            }
        }

        double output = 0.0;

        for (int index = 0; index < N; index++) {
            output += costs[0][index];
        }

        out.write(String.format("%.2f", output));

        in.close();
        out.flush();
        out.close();
    }
}