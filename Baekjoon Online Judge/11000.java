import java.io.*;
import java.util.*;

class Class {
    public int start;
    public int end;

    public Class(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Class> classes = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int S = Integer.parseInt(tokenizer.nextToken());
            int T = Integer.parseInt(tokenizer.nextToken());

            classes.add(new Class(S, T));
        }

        classes.sort(Comparator.comparingInt(o -> o.start));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int output = 0;

        for (int index = 0; index < N; index++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= classes.get(index).start) {
                priorityQueue.poll();
            }

            priorityQueue.offer(classes.get(index).end);
            output = Math.max(output, priorityQueue.size());
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}