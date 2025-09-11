import java.io.*;
import java.util.*;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class State extends Coord {
    public int time;

    public State(int x, int y, int time) {
        super(x, y);
        this.time = time;
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

        char[][] map = new char[R][C];

        Deque<Coord> water = new ArrayDeque<>();
        Deque<State> hedgehog = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        for (int row = 0; row < R; row++) {
            map[row] = in.readLine().toCharArray();

            for (int col = 0; col < C; col++) {
                if (map[row][col] == '*') {
                    water.push(new Coord(col, row));
                } else if (map[row][col] == 'S') {
                    hedgehog.push(new State(col, row, 0));
                    visited[row][col] = true;
                }
            }
        }

        String output = "KAKTUS";

        loop:
        while (!hedgehog.isEmpty()) {
            List<Coord> nextWater = new ArrayList<>();

            while (!water.isEmpty()) {
                Coord coord = water.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int x = coord.x + DX[dir];
                    int y = coord.y + DY[dir];

                    if (x < 0 || x >= C || y < 0 || y >= R || map[y][x] == '*' || map[y][x] == 'X' || map[y][x] == 'D') {
                        continue;
                    }

                    nextWater.add(new Coord(x, y));
                    map[y][x] = '*';
                }
            }

            List<State> nextStates = new ArrayList<>();

            while (!hedgehog.isEmpty()) {
                State state = hedgehog.poll();

                if (map[state.y][state.x] == 'D') {
                    output = String.valueOf(state.time);

                    break loop;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int x = state.x + DX[dir];
                    int y = state.y + DY[dir];

                    if (x < 0 || x >= C || y < 0 || y >= R || map[y][x] == '*' || map[y][x] == 'X' || visited[y][x]) {
                        continue;
                    }

                    nextStates.add(new State(x, y, state.time + 1));
                    visited[y][x] = true;
                }
            }

            water.addAll(nextWater);
            hedgehog.addAll(nextStates);
        }

        out.write(output);

        in.close();
        out.flush();
        out.close();
    }
}