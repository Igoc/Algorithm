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
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        boolean[][] aisle = new boolean[N + 1][M + 1];

        for (int index = 0; index < K; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            aisle[r][c] = true;
        }

        Queue<Pos> queue = new LinkedList<>();
        boolean[][] searchState = new boolean[N + 1][M + 1];
        int largestFoodSize = 0;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                if (!searchState[row][col] && aisle[row][col]) {
                    int currentFoodSize = 1;

                    queue.add(new Pos(col, row));
                    searchState[row][col] = true;

                    while (!queue.isEmpty()) {
                        Pos pos = queue.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int x = pos.x + DX[dir];
                            int y = pos.y + DY[dir];

                            if (x >= 1 && x <= M && y >= 1 && y <= N) {
                                if (!searchState[y][x] && aisle[y][x]) {
                                    queue.add(new Pos(x, y));
                                    searchState[y][x] = true;
                                    currentFoodSize++;
                                }
                            }
                        }
                    }

                    if (currentFoodSize > largestFoodSize) {
                        largestFoodSize = currentFoodSize;
                    }
                }
            }
        }

        out.write(String.valueOf(largestFoodSize));

        in.close();
        out.flush();
        out.close();
    }
}