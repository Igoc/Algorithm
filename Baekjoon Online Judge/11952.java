import java.io.*;
import java.util.*;

class Path {
    public int city;
    public long cost;

    public Path(int city, long cost) {
        this.city = city;
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
        int K = Integer.parseInt(tokenizer.nextToken());
        int S = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(in.readLine());

        int p = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        boolean[] zombieOccupied = new boolean[N];

        for (int index = 0; index < K; index++) {
            zombieOccupied[Integer.parseInt(in.readLine()) - 1] = true;
        }

        List<List<Integer>> roads = new ArrayList<>();

        for (int city = 0; city < N; city++) {
            roads.add(new ArrayList<>());
        }

        for (int road = 0; road < M; road++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;

            roads.get(x).add(y);
            roads.get(y).add(x);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] accommodationCosts = new int[N];

        Arrays.fill(accommodationCosts, p);
        accommodationCosts[0] = accommodationCosts[N - 1] = 0;

        for (int city = 0; city < N; city++) {
            if (zombieOccupied[city]) {
                deque.offer(city);
                accommodationCosts[city] = q;
            }
        }

        for (int distance = 1; distance <= S; distance++) {
            Deque<Integer> nextDeque = new ArrayDeque<>();

            while (!deque.isEmpty()) {
                int city = deque.poll();

                for (int nextCity : roads.get(city)) {
                    if (accommodationCosts[nextCity] == p) {
                        nextDeque.offer(nextCity);
                        accommodationCosts[nextCity] = q;
                    }
                }
            }

            deque = nextDeque;
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        long[] costs = new long[N];

        priorityQueue.offer(new Path(0, 0L));
        Arrays.fill(costs, Long.MAX_VALUE);

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (path.cost > costs[path.city]) {
                continue;
            }

            for (int nextCity : roads.get(path.city)) {
                if (!zombieOccupied[nextCity] && path.cost + accommodationCosts[nextCity] < costs[nextCity]) {
                    priorityQueue.offer(new Path(nextCity, path.cost + accommodationCosts[nextCity]));
                    costs[nextCity] = path.cost + accommodationCosts[nextCity];
                }
            }
        }

        out.write(String.valueOf(costs[N - 1]));

        in.close();
        out.flush();
        out.close();
    }
}