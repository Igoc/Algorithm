import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {
    public int position;
    public int time;

    public Pos(int position, int time) {
        this.position = position;
        this.time = time;
    }

    @Override
    public int compareTo(Pos o) {
        if (time == o.time) {
            return Integer.compare(position, o.position);
        }

        return Integer.compare(time, o.time);
    }
}

public class Main {
    static final int MAX_POSITION = 100000;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        PriorityQueue<Pos> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[MAX_POSITION + 1];

        priorityQueue.offer(new Pos(N, 0));
        visit[N] = true;

        while (priorityQueue.peek().position != K) {
            Pos pos = priorityQueue.poll();

            if (pos.position * 2 <= MAX_POSITION && !visit[pos.position * 2]) {
                priorityQueue.offer(new Pos(pos.position * 2, pos.time));
                visit[pos.position * 2] = true;
            }

            if (pos.position - 1 >= 0 && !visit[pos.position - 1]) {
                priorityQueue.offer(new Pos(pos.position - 1, pos.time + 1));
                visit[pos.position - 1] = true;
            }

            if (pos.position + 1 <= MAX_POSITION && !visit[pos.position + 1]) {
                priorityQueue.offer(new Pos(pos.position + 1, pos.time + 1));
                visit[pos.position + 1] = true;
            }
        }

        out.write(String.valueOf(priorityQueue.peek().time));

        in.close();
        out.flush();
        out.close();
    }
}