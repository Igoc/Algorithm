import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Cable implements Comparable<Cable> {
    public int computer;
    public int count;

    public Cable(int computer, int count) {
        this.computer = computer;
        this.count = count;
    }

    @Override
    public int compareTo(Cable o) {
        return Integer.compare(count, o.count);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int P = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[][] costs = new int[N][N];
        int minimumCost = 0;
        int maximumCost = 0;

        Arrays.stream(costs)
                .forEach(cost -> Arrays.fill(cost, Integer.MAX_VALUE));

        for (int cable = 0; cable < P; cable++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());

            costs[x][y] = Math.min(costs[x][y], cost);
            costs[y][x] = Math.min(costs[y][x], cost);
            maximumCost = Math.max(maximumCost, cost);
        }

        int output = -1;

        while (minimumCost <= maximumCost) {
            int cost = (minimumCost + maximumCost) / 2;

            PriorityQueue<Cable> priorityQueue = new PriorityQueue<>();
            int[] counts = new int[N];

            Arrays.fill(counts, Integer.MAX_VALUE);

            for (int computer = 0; computer < N; computer++) {
                if (costs[0][computer] != Integer.MAX_VALUE) {
                    counts[computer] = (costs[0][computer] <= cost) ? (0) : (1);
                    priorityQueue.offer(new Cable(computer, counts[computer]));
                }
            }

            while (!priorityQueue.isEmpty()) {
                Cable cable = priorityQueue.poll();

                if (cable.count > counts[cable.computer]) {
                    continue;
                }

                for (int computer = 0; computer < N; computer++) {
                    if (costs[cable.computer][computer] != Integer.MAX_VALUE) {
                        int count = (costs[cable.computer][computer] <= cost) ? (cable.count) : (cable.count + 1);

                        if (count < counts[computer]) {
                            counts[computer] = count;
                            priorityQueue.offer(new Cable(computer, counts[computer]));
                        }
                    }
                }
            }

            if (counts[N - 1] > K) {
                minimumCost = cost + 1;
            } else {
                maximumCost = cost - 1;
                output = cost;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}