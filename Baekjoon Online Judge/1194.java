import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class State {
    public int x;
    public int y;
    public int moveCount;
    public int keys;

    public State(int x, int y, int moveCount, int keys) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
        this.keys = keys;
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

        char[][] maze = new char[N][M];
        State initialState = null;

        for (int row = 0; row < N; row++) {
            maze[row] = in.readLine().toCharArray();

            if (initialState == null) {
                for (int col = 0; col < M; col++) {
                    if (maze[row][col] == '0') {
                        initialState = new State(col, row, 0, 0);

                        break;
                    }
                }
            }
        }

        Queue<State> queue = new LinkedList<>();
        boolean[][][] visits = new boolean[N][M][64];
        int output = -1;

        queue.offer(initialState);
        visits[initialState.y][initialState.x][0] = true;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            if (maze[currentState.y][currentState.x] == '1') {
                output = currentState.moveCount;

                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = currentState.x + DX[dir];
                int y = currentState.y + DY[dir];

                if (x < 0 || x >= M || y < 0 || y >= N || maze[y][x] == '#' || visits[y][x][currentState.keys]) {
                    continue;
                }

                State nextState = new State(x, y, currentState.moveCount + 1, currentState.keys);

                if ('a' <= maze[y][x] && maze[y][x] <= 'f') {
                    nextState.keys |= (int) Math.pow(2, maze[y][x] - 'a');
                } else if ('A' <= maze[y][x] && maze[y][x] <= 'F' && (nextState.keys & (int) Math.pow(2, maze[y][x] - 'A')) == 0) {
                    continue;
                }

                queue.offer(nextState);
                visits[y][x][nextState.keys] = true;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}