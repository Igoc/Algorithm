import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        String[] board = new String[N];

        for (int y = 0; y < N; y++) {
            board[y] = in.readLine();
        }

        int[][] maxMoveNumbers = new int[N][M];
        int output = 0;

        maxMoveNumbers[0][0] = 1;
        calculateMaxMoveNumbers(board, new boolean[N][M], 0, 0, maxMoveNumbers);

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                output = Math.max(output, maxMoveNumbers[y][x]);
            }
        }

        if (output == Integer.MAX_VALUE) {
            output = -1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static void calculateMaxMoveNumbers(String[] board, boolean[][] visited, int x, int y, int[][] maxMoveNumbers) {
        visited[y][x] = true;

        for (int dir = 0; dir < 4; dir++) {
            int nextX = x + DX[dir] * (board[y].charAt(x) - '0');
            int nextY = y + DY[dir] * (board[y].charAt(x) - '0');

            if (nextX >= 0 && nextX < board[0].length() && nextY >= 0 && nextY < board.length && board[nextY].charAt(nextX) != 'H') {
                if (visited[nextY][nextX]) {
                    maxMoveNumbers[nextY][nextX] = Integer.MAX_VALUE;
                } else if (maxMoveNumbers[y][x] + 1 > maxMoveNumbers[nextY][nextX]) {
                    maxMoveNumbers[nextY][nextX] = maxMoveNumbers[y][x] + 1;
                    calculateMaxMoveNumbers(board, visited, nextX, nextY, maxMoveNumbers);
                }
            }
        }

        visited[y][x] = false;
    }
}