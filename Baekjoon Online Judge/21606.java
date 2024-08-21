import java.io.*;
import java.util.*;

class Node {
    public List<Integer> edges;
    public boolean indoor;
    public long adjacentIndoorNumber;

    public Node(boolean indoor) {
        this.edges = new ArrayList<>();
        this.indoor = indoor;
        this.adjacentIndoorNumber = 0L;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        String A = in.readLine();

        Node[] nodes = new Node[N];

        for (int index = 0; index < N; index++) {
            nodes[index] = new Node(A.charAt(index) == '1');
        }

        for (int index = 0; index < N - 1; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;

            nodes[u].edges.add(v);
            nodes[v].edges.add(u);

            if (nodes[u].indoor) {
                nodes[v].adjacentIndoorNumber++;
            }

            if (nodes[v].indoor) {
                nodes[u].adjacentIndoorNumber++;
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visits = new boolean[N];
        long output = 0L;

        for (int index = 0; index < N; index++) {
            if (nodes[index].indoor) {
                output += nodes[index].adjacentIndoorNumber;
            } else if (!visits[index]) {
                long indoorNumber = 0L;

                deque.offer(index);
                visits[index] = true;

                while (!deque.isEmpty()) {
                    int currentIndex = deque.poll();

                    indoorNumber += nodes[currentIndex].adjacentIndoorNumber;

                    for (int nextIndex : nodes[currentIndex].edges) {
                        if (!nodes[nextIndex].indoor && !visits[nextIndex]) {
                            deque.offer(nextIndex);
                            visits[nextIndex] = true;
                        }
                    }
                }

                output += indoorNumber * (indoorNumber - 1);
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}