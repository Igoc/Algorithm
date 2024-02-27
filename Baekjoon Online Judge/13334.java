import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class LineSegment {
    public int start;
    public int end;

    public LineSegment(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        LineSegment[] lineSegments = new LineSegment[n];

        for (int index = 0; index < n; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int h = Integer.parseInt(tokenizer.nextToken());
            int o = Integer.parseInt(tokenizer.nextToken());

            if (h < o) {
                lineSegments[index] = new LineSegment(h, o);
            } else {
                lineSegments[index] = new LineSegment(o, h);
            }
        }

        int d = Integer.parseInt(in.readLine());

        Arrays.sort(lineSegments, Comparator.comparingInt(o -> o.end - d));

        PriorityQueue<LineSegment> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        int output = 0;

        for (LineSegment lineSegment : lineSegments) {
            if (lineSegment.end - lineSegment.start > d) {
                continue;
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek().start + d < lineSegment.end) {
                priorityQueue.poll();
            }

            priorityQueue.offer(lineSegment);

            output = Math.max(output, priorityQueue.size());
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}