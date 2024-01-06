import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < K; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int V = Integer.parseInt(tokenizer.nextToken());
            int E = Integer.parseInt(tokenizer.nextToken());

            List<List<Integer>> edges = new ArrayList<>();

            for (int vertex = 0; vertex < V; vertex++) {
                edges.add(new ArrayList<>());
            }

            for (int edge = 0; edge < E; edge++) {
                tokenizer = new StringTokenizer(in.readLine());

                int u = Integer.parseInt(tokenizer.nextToken()) - 1;
                int v = Integer.parseInt(tokenizer.nextToken()) - 1;

                edges.get(u).add(v);
                edges.get(v).add(u);
            }

            Stack<Integer> stack = new Stack<>();
            int[] groups = new int[V];
            boolean bipartiteGraph = true;

            loop:
            for (int vertex = 0; vertex < V; vertex++) {
                if (groups[vertex] != 0) {
                    continue;
                }

                stack.push(vertex);
                groups[vertex] = 1;

                while (!stack.isEmpty()) {
                    int currentVertex = stack.pop();

                    for (int nextVertex : edges.get(currentVertex)) {
                        if (groups[nextVertex] == 0) {
                            stack.push(nextVertex);
                            groups[nextVertex] = (groups[currentVertex] == 1) ? (2) : (1);
                        } else if (groups[nextVertex] == groups[currentVertex]) {
                            bipartiteGraph = false;

                            break loop;
                        }
                    }
                }
            }

            out.write((bipartiteGraph) ? ("YES\n") : ("NO\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}