import java.io.*;
import java.util.*;

class Kinship {
    public int index;
    public int degree;

    public Kinship(int index, int degree) {
        this.index = index;
        this.degree = degree;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[] targets = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(index -> index - 1)
                .toArray();

        int m = Integer.parseInt(in.readLine());

        List<List<Integer>> parents = new ArrayList<>();
        List<List<Integer>> children = new ArrayList<>();

        for (int index = 0; index < n; index++) {
            parents.add(new ArrayList<>());
            children.add(new ArrayList<>());
        }

        for (int index = 0; index < m; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;

            parents.get(y).add(x);
            children.get(x).add(y);
        }

        Queue<Kinship> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int output = -1;

        queue.offer(new Kinship(targets[0], 0));
        visited[targets[0]] = true;

        while (!queue.isEmpty()) {
            Kinship kinship = queue.poll();

            if (kinship.index == targets[1]) {
                output = kinship.degree;
                break;
            }

            for (int index : parents.get(kinship.index)) {
                if (!visited[index]) {
                    queue.offer(new Kinship(index, kinship.degree + 1));
                    visited[index] = true;
                }
            }

            for (int index : children.get(kinship.index)) {
                if (!visited[index]) {
                    queue.offer(new Kinship(index, kinship.degree + 1));
                    visited[index] = true;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}