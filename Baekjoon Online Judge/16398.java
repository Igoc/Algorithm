import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Flow {
    public int planet;
    public int cost;

    public Flow(int planet, int cost) {
        this.planet = planet;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] costs = new int[N][N];

        for (int row = 0; row < N; row++) {
            costs[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        PriorityQueue<Flow> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        boolean[] visits = new boolean[N];
        long output = 0L;

        priorityQueue.offer(new Flow(0, 0));

        while (!priorityQueue.isEmpty()) {
            Flow flow = priorityQueue.poll();

            if (visits[flow.planet]) {
                continue;
            }

            visits[flow.planet] = true;
            output += flow.cost;

            for (int planet = 0; planet < N; planet++) {
                if (!visits[planet]) {
                    priorityQueue.offer(new Flow(planet, costs[flow.planet][planet]));
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}