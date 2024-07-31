import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

class Range {
    public int start;
    public int end;
    public int team;

    public Range(int start, int end, int team) {
        this.start = start;
        this.end = end;
        this.team = team;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Deque<Range> deque = new ArrayDeque<>();

        deque.offer(new Range(0, N, 0));

        for (int date = 0; date < 7; date++) {
            Deque<Range> nextDeque = new ArrayDeque<>();

            while (!deque.isEmpty()) {
                Range range = deque.poll();
                int mid = (range.start + range.end) / 2;

                nextDeque.offer(new Range(range.start, mid, range.team));
                nextDeque.offer(new Range(mid, range.end, (range.team + 1) % 2));

                for (int index = range.start; index < mid; index++) {
                    out.write(range.team + 'A');
                }

                for (int index = mid; index < range.end; index++) {
                    out.write((range.team + 1) % 2 + 'A');
                }
            }

            deque = nextDeque;
            out.write('\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}