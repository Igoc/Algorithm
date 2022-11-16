import java.io.*;
import java.util.*;

class Pos implements Comparable<Pos> {
    public int x;
    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pos o) {
        if (x == o.x) {
            return y - o.y;
        }

        return x - o.x;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Pos> positions = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            positions.add(new Pos(x, y));
        }

        positions.sort(Comparator.naturalOrder());

        for (Pos position : positions) {
            out.write(position.x + " " + position.y + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}