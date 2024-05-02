import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    public int vertex;
    public int cost;

    public Edge(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());

        List<List<Edge>> costs = new ArrayList<>();

        for (int vertex = 0; vertex < V; vertex++) {
            costs.add(new ArrayList<>());
        }

        for (int edge = 0; edge < E; edge++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            int C = Integer.parseInt(tokenizer.nextToken());

            costs.get(A).add(new Edge(B, C));
            costs.get(B).add(new Edge(A, C));
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        boolean[] visits = new boolean[V];
        int output = 0;

        priorityQueue.offer(new Edge(0, 0));

        while (!priorityQueue.isEmpty()) {
            Edge currentEdge = priorityQueue.poll();

            if (visits[currentEdge.vertex]) {
                continue;
            }

            visits[currentEdge.vertex] = true;
            output += currentEdge.cost;

            for (Edge nextEdge : costs.get(currentEdge.vertex)) {
                if (!visits[nextEdge.vertex]) {
                    priorityQueue.offer(new Edge(nextEdge.vertex, nextEdge.cost));
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}