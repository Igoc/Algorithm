import java.io.*;
import java.util.*;

class Conference {
    public int start;
    public int end;

    public Conference(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Conference> conferences = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            conferences.add(new Conference(start, end));
        }

        conferences.sort(Comparator.comparingInt(o -> o.start));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int output = 0;

        for (Conference conference : conferences) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= conference.start) {
                priorityQueue.poll();
            }

            priorityQueue.offer(conference.end);
            output = Math.max(output, priorityQueue.size());
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}