import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Path implements Comparable<Path> {
    public int city;
    public int cost;

    public Path(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        int[][] buses = new int[N][N];

        Arrays.stream(buses)
                .forEach(bus -> Arrays.fill(bus, Integer.MAX_VALUE));

        for (int index = 0; index < M; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int origin = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destination = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());

            buses[origin][destination] = Math.min(buses[origin][destination], cost);
        }

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int origin = Integer.parseInt(tokenizer.nextToken()) - 1;
        int destination = Integer.parseInt(tokenizer.nextToken()) - 1;

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[N];

        for (int city = 0; city < N; city++) {
            if (buses[origin][city] != Integer.MAX_VALUE) {
                priorityQueue.offer(new Path(city, buses[origin][city]));
            }
        }

        buses[origin][origin] = 0;
        visit[origin] = true;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visit[path.city]) {
                continue;
            }

            visit[path.city] = true;

            for (int city = 0; city < N; city++) {
                if (buses[path.city][city] != Integer.MAX_VALUE && buses[origin][path.city] + buses[path.city][city] < buses[origin][city]) {
                    buses[origin][city] = buses[origin][path.city] + buses[path.city][city];
                    priorityQueue.offer(new Path(city, buses[origin][city]));
                }
            }
        }

        out.write(String.valueOf(buses[origin][destination]));

        in.close();
        out.flush();
        out.close();
    }
}