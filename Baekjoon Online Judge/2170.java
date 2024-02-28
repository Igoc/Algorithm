import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Line implements Comparable<Line> {
    public int start;
    public int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(start, o.start);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Line[] lines = new Line[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            lines[index] = new Line(x, y);
        }

        Arrays.sort(lines);

        long start = lines[0].start;
        long end = lines[0].end;
        long output = 0L;

        for (int index = 1; index < N; index++) {
            if (lines[index].start > end) {
                output += end - start;
                start = lines[index].start;
                end = lines[index].end;

                continue;
            }

            if (lines[index].start < start) {
                start = lines[index].start;
            }

            if (lines[index].end > end) {
                end = lines[index].end;
            }
        }

        output += end - start;

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}