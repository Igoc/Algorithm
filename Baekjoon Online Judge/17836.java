import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class Coord {
    public int x;
    public int y;
    public int time;
    public int gram;

    public Coord(int x, int y, int time, int gram) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.gram = gram;
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());

        int[][] castle = new int[N][M];

        for (int row = 0; row < N; row++) {
            castle[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Deque<Coord> deque = new ArrayDeque<>();
        boolean[][][] visits = new boolean[N][M][2];
        String output = "Fail";

        deque.offer(new Coord(0, 0, 0, 0));
        visits[0][0][0] = true;

        while (!deque.isEmpty()) {
            Coord coord = deque.poll();

            if (coord.time > T) {
                break;
            }

            if (coord.x == M - 1 && coord.y == N - 1) {
                output = String.valueOf(coord.time);

                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = coord.x + DX[dir];
                int y = coord.y + DY[dir];

                if (x >= 0 && x < M && y >= 0 && y < N && !visits[y][x][coord.gram]) {
                    if (castle[y][x] == 1 && coord.gram == 0) {
                        continue;
                    }

                    if (castle[y][x] == 2) {
                        deque.offer(new Coord(x, y, coord.time + 1, 1));
                        visits[y][x][1] = true;
                    } else {
                        deque.offer(new Coord(x, y, coord.time + 1, coord.gram));
                        visits[y][x][coord.gram] = true;
                    }
                }
            }
        }

        out.write(output);

        in.close();
        out.flush();
        out.close();
    }
}