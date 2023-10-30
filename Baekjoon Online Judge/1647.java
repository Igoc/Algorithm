import java.io.*;
import java.util.*;

class Path implements Comparable<Path> {
    public int house;
    public int cost;

    public Path(int house, int cost) {
        this.house = house;
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
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<Map<Integer, Integer>> costs = new ArrayList<>();

        for (int house = 0; house < N; house++) {
            costs.add(new HashMap<>());
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            int C = Integer.parseInt(tokenizer.nextToken());

            if (costs.get(A).containsKey(B)) {
                costs.get(A).replace(B, Math.min(costs.get(A).get(B), C));
            } else {
                costs.get(A).put(B, C);
            }

            if (costs.get(B).containsKey(A)) {
                costs.get(B).replace(A, Math.min(costs.get(B).get(A), C));
            } else {
                costs.get(B).put(A, C);
            }
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[N];

        priorityQueue.offer(new Path(0, 0));

        int maximumCost = 0;
        int output = 0;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visit[path.house]) {
                continue;
            }

            visit[path.house] = true;

            for (int house : costs.get(path.house).keySet()) {
                if (!visit[house]) {
                    priorityQueue.offer(new Path(house, costs.get(path.house).get(house)));
                }
            }

            maximumCost = Math.max(maximumCost, path.cost);
            output += path.cost;
        }

        out.write(String.valueOf(output - maximumCost));

        in.close();
        out.flush();
        out.close();
    }
}