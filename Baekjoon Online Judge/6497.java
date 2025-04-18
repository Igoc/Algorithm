import java.io.*;
import java.util.*;

class Path {
    public int house;
    public int cost;

    public Path(int house, int cost) {
        this.house = house;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int m = Integer.parseInt(tokenizer.nextToken());
            int n = Integer.parseInt(tokenizer.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            Map<Integer, Map<Integer, Integer>> paths = new HashMap<>();
            int totalCost = 0;

            for (int index = 0; index < n; index++) {
                tokenizer = new StringTokenizer(in.readLine());

                int x = Integer.parseInt(tokenizer.nextToken());
                int y = Integer.parseInt(tokenizer.nextToken());
                int z = Integer.parseInt(tokenizer.nextToken());

                paths.putIfAbsent(x, new HashMap<>());
                paths.putIfAbsent(y, new HashMap<>());

                paths.get(x).put(y, z);
                paths.get(y).put(x, z);
                totalCost += z;
            }

            PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
            boolean[] visited = new boolean[m];
            int minimumCost = 0;

            priorityQueue.offer(new Path(0, 0));

            while (!priorityQueue.isEmpty()) {
                Path path = priorityQueue.poll();

                if (visited[path.house]) {
                    continue;
                }

                visited[path.house] = true;
                minimumCost += path.cost;

                for (int house : paths.get(path.house).keySet()) {
                    if (!visited[house]) {
                        priorityQueue.offer(new Path(house, paths.get(path.house).get(house)));
                    }
                }
            }

            out.write((totalCost - minimumCost) + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}