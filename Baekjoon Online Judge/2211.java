import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Line implements Comparable<Line> {
    public int source;
    public int destination;
    public int cost;

    public Line(int source, int destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] costs = new int[N][N];

        Arrays.stream(costs)
                .forEach(cost -> Arrays.fill(cost, Integer.MAX_VALUE));

        for (int line = 0; line < M; line++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            int C = Integer.parseInt(tokenizer.nextToken());

            costs[A][B] = Math.min(costs[A][B], C);
            costs[B][A] = Math.min(costs[B][A], C);
        }

        PriorityQueue<Line> priorityQueue = new PriorityQueue<>();
        boolean[] visits = new boolean[N];

        visits[0] = true;

        for (int computer = 0; computer < N; computer++) {
            if (costs[0][computer] != Integer.MAX_VALUE) {
                priorityQueue.offer(new Line(0, computer, costs[0][computer]));
            }
        }

        out.write((N - 1) + "\n");

        while (!priorityQueue.isEmpty()) {
            Line line = priorityQueue.poll();

            if (visits[line.destination]) {
                continue;
            }

            visits[line.destination] = true;

            for (int computer = 0; computer < N; computer++) {
                if (!visits[computer] && costs[line.destination][computer] != Integer.MAX_VALUE) {
                    priorityQueue.offer(new Line(line.destination, computer, line.cost + costs[line.destination][computer]));
                }
            }

            out.write((line.source + 1) + " " + (line.destination + 1) + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}