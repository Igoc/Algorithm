import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
    public int index;
    public int level;
    public Node parent;

    public Node(int index) {
        this.index = index;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Node[] nodes = new Node[N + 1];
        List<List<Integer>> edges = new ArrayList<>();

        for (int index = 0; index <= N; index++) {
            nodes[index] = new Node(index);
            edges.add(new ArrayList<>());
        }

        for (int edge = 0; edge < N - 1; edge++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            edges.get(x).add(y);
            edges.get(y).add(x);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visit = new boolean[N + 1];

        stack.push(1);
        visit[1] = true;

        while (!stack.isEmpty()) {
            int parent = stack.pop();

            for (int child : edges.get(parent)) {
                if (visit[child]) {
                    continue;
                }

                nodes[child].level = nodes[parent].level + 1;
                nodes[child].parent = nodes[parent];

                stack.push(child);
                visit[child] = true;
            }
        }

        int M = Integer.parseInt(in.readLine());

        for (int pair = 0; pair < M; pair++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            Node x = nodes[Integer.parseInt(tokenizer.nextToken())];
            Node y = nodes[Integer.parseInt(tokenizer.nextToken())];

            while (x.level > y.level) {
                x = x.parent;
            }

            while (y.level > x.level) {
                y = y.parent;
            }

            while (x != y) {
                x = x.parent;
                y = y.parent;
            }

            out.write(x.index + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}