import java.io.*;
import java.util.*;

class Path implements Comparable<Path> {
    public int barn;
    public int cost;

    public Path(int barn, int cost) {
        this.barn = barn;
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

        for (int barn = 0; barn < N; barn++) {
            costs.add(new HashMap<>());
        }

        for (int path = 0; path < M; path++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A_i = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B_i = Integer.parseInt(tokenizer.nextToken()) - 1;
            int C_i = Integer.parseInt(tokenizer.nextToken());

            if (costs.get(A_i).containsKey(B_i)) {
                costs.get(A_i).replace(B_i, Math.min(costs.get(A_i).get(B_i), C_i));
            } else {
                costs.get(A_i).put(B_i, C_i);
            }

            if (costs.get(B_i).containsKey(A_i)) {
                costs.get(B_i).replace(A_i, Math.min(costs.get(B_i).get(A_i), C_i));
            } else {
                costs.get(B_i).put(A_i, C_i);
            }
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();

        for (int barn : costs.get(0).keySet()) {
            priorityQueue.offer(new Path(barn, costs.get(0).get(barn)));
        }

        costs.get(0).put(0, 0);

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (path.cost > costs.get(0).get(path.barn)) {
                continue;
            }

            for (int barn : costs.get(path.barn).keySet()) {
                if (!costs.get(0).containsKey(barn) || path.cost + costs.get(path.barn).get(barn) < costs.get(0).get(barn)) {
                    costs.get(0).put(barn, path.cost + costs.get(path.barn).get(barn));
                    priorityQueue.offer(new Path(barn, costs.get(0).get(barn)));
                }
            }
        }

        out.write(String.valueOf(costs.get(0).get(N - 1)));

        in.close();
        out.flush();
        out.close();
    }
}