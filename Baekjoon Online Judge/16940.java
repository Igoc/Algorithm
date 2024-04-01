import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Set<Integer>> edges = new ArrayList<>();

        for (int vertex = 0; vertex < N; vertex++) {
            edges.add(new HashSet<>());
        }

        for (int edge = 0; edge < N - 1; edge++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;

            edges.get(x).add(y);
            edges.get(y).add(x);
        }

        int[] orders = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(vertex -> vertex - 1)
                .toArray();

        Queue<Integer> queue = new LinkedList<>();
        int output = 0;

        if (orders[0] == 0) {
            int index = 1;

            queue.offer(0);
            output = 1;

            while (!queue.isEmpty()) {
                while (!queue.isEmpty() && index < N && !edges.get(queue.peek()).contains(orders[index])) {
                    queue.poll();
                }

                if (queue.isEmpty()) {
                    output = 0;

                    break;
                }

                int vertex = queue.poll();

                while (index < N && edges.get(vertex).contains(orders[index])) {
                    queue.offer(orders[index++]);
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}