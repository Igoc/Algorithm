import java.io.*;
import java.util.*;

class State {
    public int x;
    public int y;
    public Set<Integer> sweetPotatoes;

    public State(int x, int y, Set<Integer> sweetPotatoes) {
        this.x = x;
        this.y = y;
        this.sweetPotatoes = sweetPotatoes;
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
        int T = Integer.parseInt(tokenizer.nextToken());

        char[][] map = new char[R][C];
        Deque<State> deque = new ArrayDeque<>();

        for (int y = 0; y < R; y++) {
            String row = in.readLine();

            for (int x = 0; x < C; x++) {
                map[y][x] = row.charAt(x);

                if (map[y][x] == 'G') {
                    deque.offer(new State(x, y, new HashSet<>()));
                }
            }
        }

        int output = 0;

        for (int time = 0; time < T; time++) {
            Deque<State> nextDeque = new ArrayDeque<>();

            while (!deque.isEmpty()) {
                State state = deque.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int x = state.x + DX[dir];
                    int y = state.y + DY[dir];

                    if (x < 0 || x >= C || y < 0 || y >= R || map[y][x] == '#') {
                        continue;
                    }

                    Set<Integer> sweetPotatoes = new HashSet<>(state.sweetPotatoes);

                    if (map[y][x] == 'S') {
                        sweetPotatoes.add(y * C + x);
                    }

                    nextDeque.offer(new State(x, y, sweetPotatoes));
                    output = Math.max(output, sweetPotatoes.size());
                }
            }

            deque = nextDeque;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}