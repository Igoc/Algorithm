import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
    public int x;
    public int y;
    public int moveCount;
    public int remainingAbilityNumber;

    public Pos(int x, int y, int moveCount, int remainingAbilityNumber) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
        this.remainingAbilityNumber = remainingAbilityNumber;
    }
}

public class Main {
    static final int[] MONKEY_DX = {0, 1, 0, -1};
    static final int[] MONKEY_DY = {-1, 0, 1, 0};

    static final int[] HORSE_DX = {1, 2, 2, 1, -1, -2, -2, -1};
    static final int[] HORSE_DY = {-2, -1, 1, 2, 2, 1, -1, -2};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(in.readLine());

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int W = Integer.parseInt(tokenizer.nextToken());
        int H = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[H][W];

        for (int row = 0; row < H; row++) {
            map[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Queue<Pos> queue = new LinkedList<>();
        boolean[][][] visits = new boolean[H][W][K + 1];
        int output = -1;

        queue.offer(new Pos(0, 0, 0, K));
        visits[0][0][K] = true;

        while (!queue.isEmpty()) {
            Pos pos = queue.poll();

            if (pos.x == W - 1 && pos.y == H - 1) {
                output = pos.moveCount;

                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = pos.x + MONKEY_DX[dir];
                int y = pos.y + MONKEY_DY[dir];

                if (x >= 0 && x < W && y >= 0 && y < H && map[y][x] == 0 && !visits[y][x][pos.remainingAbilityNumber]) {
                    queue.offer(new Pos(x, y, pos.moveCount + 1, pos.remainingAbilityNumber));
                    visits[y][x][pos.remainingAbilityNumber] = true;
                }
            }

            if (pos.remainingAbilityNumber > 0) {
                for (int dir = 0; dir < 8; dir++) {
                    int x = pos.x + HORSE_DX[dir];
                    int y = pos.y + HORSE_DY[dir];

                    if (x >= 0 && x < W && y >= 0 && y < H && map[y][x] == 0 && !visits[y][x][pos.remainingAbilityNumber - 1]) {
                        queue.offer(new Pos(x, y, pos.moveCount + 1, pos.remainingAbilityNumber - 1));
                        visits[y][x][pos.remainingAbilityNumber - 1] = true;
                    }
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}