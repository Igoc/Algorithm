import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Cost {
    public int x;
    public int y;
    public int value;

    public Cost(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] W = new int[N];

        for (int index = 0; index < N; index++) {
            W[index] = Integer.parseInt(in.readLine());
        }

        int[][] P = new int[N][N];

        for (int index = 0; index < N; index++) {
            P[index] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            P[index][index] = W[index];
        }

        PriorityQueue<Cost> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        for (int x = 0; x < N; x++) {
            for (int y = x; y < N; y++) {
                priorityQueue.offer(new Cost(x, y, P[x][y]));
            }
        }

        int[] parents = new int[N];
        boolean[] water = new boolean[N];
        int output = 0;

        for (int index = 0; index < N; index++) {
            parents[index] = index;
        }

        while (!priorityQueue.isEmpty()) {
            Cost cost = priorityQueue.poll();

            find(parents, water, cost.x);
            find(parents, water, cost.y);

            if (cost.x == cost.y && !water[parents[cost.x]]) {
                water[cost.x] = water[parents[cost.x]] = true;
                output += cost.value;
            } else if (parents[cost.x] != parents[cost.y] && (!water[parents[cost.x]] || !water[parents[cost.y]])) {
                union(parents, water, cost.x, cost.y);
                output += cost.value;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static void union(int[] parents, boolean[] water, int x, int y) {
        x = find(parents, water, x);
        y = find(parents, water, y);

        if (water[x] || water[y]) {
            water[x] = water[y] = true;
        }

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }

    public static int find(int[] parents, boolean[] water, int x) {
        if (parents[x] == x) {
            return x;
        }

        if (water[x] || water[parents[x]]) {
            water[x] = water[parents[x]] = true;
        }

        return parents[x] = find(parents, water, parents[x]);
    }
}