import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Pos {
    public int x;
    public int y;

    public Pos(int x, int y) {
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

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(in.readLine());

        int x1 = Integer.parseInt(tokenizer.nextToken()) - 1;
        int y1 = Integer.parseInt(tokenizer.nextToken()) - 1;
        int x2 = Integer.parseInt(tokenizer.nextToken()) - 1;
        int y2 = Integer.parseInt(tokenizer.nextToken()) - 1;

        StringBuilder[] classroom = new StringBuilder[N];

        for (int col = 0; col < N; col++) {
            classroom[col] = new StringBuilder(in.readLine());
        }

        int output = 1;

        loop:
        while (true) {
            Deque<Pos> deque = new ArrayDeque<>();
            boolean[][] visits = new boolean[N][M];

            deque.offer(new Pos(x1, y1));
            visits[x1][y1] = true;

            while (!deque.isEmpty()) {
                Pos pos = deque.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int x = pos.x + DX[dir];
                    int y = pos.y + DY[dir];

                    if (x >= 0 && x < N && y >= 0 && y < M && !visits[x][y]) {
                        char space = classroom[x].charAt(y);

                        visits[x][y] = true;

                        if (space == '0') {
                            deque.offer(new Pos(x, y));
                        } else if (space == '1') {
                            classroom[x].setCharAt(y, '0');
                        } else if (space == '#') {
                            break loop;
                        }
                    }
                }
            }

            output++;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}