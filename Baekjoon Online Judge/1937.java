import java.io.*;
import java.util.Arrays;

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int calculateMoveCount(int[][] bambooGrove, int[][] moveCount, boolean[][] visit, int x, int y) {
        if (visit[y][x]) {
            return moveCount[y][x];
        }

        visit[y][x] = true;

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + DX[dir];
            int newY = y + DY[dir];

            if (newX >= 0 && newX < bambooGrove[0].length && newY >= 0 && newY < bambooGrove.length && bambooGrove[newY][newX] > bambooGrove[y][x]) {
                moveCount[y][x] = Math.max(moveCount[y][x], calculateMoveCount(bambooGrove, moveCount, visit, newX, newY) + 1);
            }
        }

        return moveCount[y][x];
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[][] bambooGrove = new int[n][n];
        int[][] moveCount = new int[n][n];
        boolean[][] visit = new boolean[n][n];

        for (int index = 0; index < n; index++) {
            bambooGrove[index] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Arrays.fill(moveCount[index], 1);
        }

        int output = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                output = Math.max(output, calculateMoveCount(bambooGrove, moveCount, visit, col, row));
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}