import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class State {
    public int x;
    public int y;
    public int distance;
    public int destruction;

    public State(int x, int y, int distance, int destruction) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.destruction = destruction;
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

        String[] map = new String[N];

        for (int row = 0; row < N; row++) {
            map[row] = in.readLine();
        }

        Queue<State> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[N][M][2];
        int output = Integer.MAX_VALUE;

        queue.offer(new State(0, 0, 1, 0));
        visit[0][0][0] = true;

        while (!queue.isEmpty()) {
            State state = queue.poll();

            if (state.x == M - 1 && state.y == N - 1) {
                output = state.distance;

                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = state.x + DX[dir];
                int y = state.y + DY[dir];

                if (x >= 0 && x < M && y >= 0 && y < N) {
                    if (map[y].charAt(x) == '0' && !visit[y][x][state.destruction]) {
                        queue.offer(new State(x, y, state.distance + 1, state.destruction));
                        visit[y][x][state.destruction] = true;
                    } else if (map[y].charAt(x) == '1' && state.destruction == 0 && !visit[y][x][1]) {
                        queue.offer(new State(x, y, state.distance + 1, 1));
                        visit[y][x][1] = true;
                    }
                }
            }
        }

        out.write(String.valueOf((output == Integer.MAX_VALUE) ? (-1) : (output)));

        in.close();
        out.flush();
        out.close();
    }
}