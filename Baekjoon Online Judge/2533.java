import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    public int index;
    public List<Node> friends;
    public int[] earlyAdaptorNumbers;
    public boolean visit;

    public Node(int index) {
        this.index = index;
        this.friends = new ArrayList<>();
        this.earlyAdaptorNumbers = new int[2];
        this.visit = false;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Node[] nodes = new Node[N];

        for (int index = 0; index < N; index++) {
            nodes[index] = new Node(index);
        }

        for (int index = 0; index < N - 1; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;

            nodes[u].friends.add(nodes[v]);
            nodes[v].friends.add(nodes[u]);
        }

        calculateEarlyAdaptorNumbers(nodes[0]);

        out.write(String.valueOf(Math.min(nodes[0].earlyAdaptorNumbers[0], nodes[0].earlyAdaptorNumbers[1])));

        in.close();
        out.flush();
        out.close();
    }

    public static void calculateEarlyAdaptorNumbers(Node node) {
        node.earlyAdaptorNumbers[0] = 0;
        node.earlyAdaptorNumbers[1] = 1;
        node.visit = true;

        for (Node friend : node.friends) {
            if (!friend.visit) {
                calculateEarlyAdaptorNumbers(friend);

                node.earlyAdaptorNumbers[0] += friend.earlyAdaptorNumbers[1];
                node.earlyAdaptorNumbers[1] += Math.min(friend.earlyAdaptorNumbers[0], friend.earlyAdaptorNumbers[1]);
            }
        }
    }
}