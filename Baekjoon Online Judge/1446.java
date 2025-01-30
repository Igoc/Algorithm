import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Shortcut {
    public int start;
    public int end;
    public int distance;

    public Shortcut(int start, int end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        Shortcut[] shortcuts = new Shortcut[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int distance = Integer.parseInt(tokenizer.nextToken());

            shortcuts[index] = new Shortcut(start, end, distance);
        }

        Arrays.sort(shortcuts, Comparator.comparingInt(o -> o.start));

        int[] expressway = new int[D + 1];

        for (int distance = 1; distance <= D; distance++) {
            expressway[distance] = distance;
        }

        int index = 0;

        for (int distance = 0; distance <= D; distance++) {
            if (distance > 0) {
                expressway[distance] = Math.min(expressway[distance], expressway[distance - 1] + 1);
            }

            while (index < N && shortcuts[index].start == distance) {
                Shortcut shortcut = shortcuts[index++];

                if (shortcut.end <= D) {
                    expressway[shortcut.end] = Math.min(expressway[shortcut.end], expressway[shortcut.start] + shortcut.distance);
                }
            }
        }

        out.write(String.valueOf(expressway[D]));

        in.close();
        out.flush();
        out.close();
    }
}