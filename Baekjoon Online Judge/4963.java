import java.io.*;
import java.util.*;

class Pos {
    public int x;
    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int w = Integer.parseInt(tokenizer.nextToken());
            int h = Integer.parseInt(tokenizer.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            String[] map = new String[h];

            for (int row = 0; row < h; row++) {
                map[row] = in.readLine().replace(" ", "");
            }

            Queue<Pos> queue = new LinkedList<>();
            boolean[][] searchState = new boolean[h][w];
            int islandNumber = 0;

            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    if (!searchState[row][col] && map[row].charAt(col) == '1') {
                        queue.offer(new Pos(col, row));
                        searchState[row][col] = true;
                        islandNumber++;

                        while (!queue.isEmpty()) {
                            Pos pos = queue.poll();

                            for (int dir = 0; dir < 8; dir++) {
                                int x = pos.x + DX[dir];
                                int y = pos.y + DY[dir];

                                if (x >= 0 && x < w && y >= 0 && y < h) {
                                    if (!searchState[y][x] && map[y].charAt(x) == '1') {
                                        queue.offer(new Pos(x, y));
                                        searchState[y][x] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            out.write(String.valueOf(islandNumber) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}