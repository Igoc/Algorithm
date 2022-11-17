import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> edges = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            edges.add(new ArrayList<>());
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;

            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] searchState = new boolean[N];
        int connectedComponentNumber = 0;

        for (int index = 0; index < N; index++) {
            if (!searchState[index]) {
                searchState[index] = true;
                connectedComponentNumber++;

                for (int node : edges.get(index)) {
                    if (!searchState[node]) {
                        stack.push(node);
                        searchState[node] = true;
                    }
                }

                while (!stack.isEmpty()) {
                    int currentIndex = stack.pop();

                    for (int node : edges.get(currentIndex)) {
                        if (!searchState[node]) {
                            stack.push(node);
                            searchState[node] = true;
                        }
                    }
                }
            }
        }

        out.write(String.valueOf(connectedComponentNumber));

        in.close();
        out.flush();
        out.close();
    }
}