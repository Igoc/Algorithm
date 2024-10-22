import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        char[][] maze = new char[R][C];

        for (int row = 0; row < R; row++) {
            maze[row] = in.readLine().toCharArray();
        }

        Deque<Coord> jihunDeque = new ArrayDeque<>();
        Deque<Coord> fireDeque = new ArrayDeque<>();
        int[][] visits = new int[R][C];

        Arrays.stream(visits)
                .forEach(visit -> Arrays.fill(visit, Integer.MAX_VALUE));

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (maze[row][col] == 'J') {
                    maze[row][col] = '.';
                    jihunDeque.offer(new Coord(col, row));
                    visits[row][col] = 1;
                } else if (maze[row][col] == 'F') {
                    fireDeque.offer(new Coord(col, row));
                }
            }
        }

        int previousTime = 1;
        String output = "IMPOSSIBLE";

        while (!jihunDeque.isEmpty()) {
            Coord jihun = jihunDeque.poll();

            if (visits[jihun.y][jihun.x] != previousTime) {
                Deque<Coord> nextFireDeque = new ArrayDeque<>();

                for (Coord fire : fireDeque) {
                    for (int dir = 0; dir < 4; dir++) {
                        int x = fire.x + DX[dir];
                        int y = fire.y + DY[dir];

                        if (isInsideMaze(x, y, C, R) && maze[y][x] == '.') {
                            maze[y][x] = 'F';
                            nextFireDeque.offer(new Coord(x, y));
                        }
                    }
                }

                previousTime = visits[jihun.y][jihun.x];
                fireDeque = nextFireDeque;
            }

            if (isBorder(jihun.x, jihun.y, C, R) && maze[jihun.y][jihun.x] == '.') {
                output = String.valueOf(visits[jihun.y][jihun.x]);
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = jihun.x + DX[dir];
                int y = jihun.y + DY[dir];

                if (isInsideMaze(x, y, C, R) && visits[y][x] == Integer.MAX_VALUE && maze[y][x] == '.') {
                    jihunDeque.offer(new Coord(x, y));
                    visits[y][x] = visits[jihun.y][jihun.x] + 1;
                }
            }
        }

        out.write(output);

        in.close();
        out.flush();
        out.close();
    }

    public static boolean isBorder(int x, int y, int width, int height) {
        return x == 0 || x == width - 1 || y == 0 || y == height - 1;
    }

    public static boolean isInsideMaze(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}