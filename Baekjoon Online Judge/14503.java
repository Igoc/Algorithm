import java.io.*;
import java.util.Arrays;
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

        tokenizer = new StringTokenizer(in.readLine());

        int r = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        int d = Integer.parseInt(tokenizer.nextToken());

        int[][] room = new int[N][M];

        for (int row = 0; row < N; row++) {
            room[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int output = 0;

        cleaningLoop:
        while (true) {
            if (room[r][c] == 0) {
                room[r][c] = 2;
                output++;
            }

            for (int dir = 3; dir >= 0; dir--) {
                int x = c + DX[(d + dir) % 4];
                int y = r + DY[(d + dir) % 4];

                if (x >= 0 && x < M && y >= 0 && y < N && room[y][x] == 0) {
                    r = y;
                    c = x;
                    d = (d + dir) % 4;

                    continue cleaningLoop;
                }
            }

            int x = c + DX[(d + 2) % 4];
            int y = r + DY[(d + 2) % 4];

            if (x >= 0 && x < M && y >= 0 && y < N) {
                if (room[y][x] == 1) {
                    break;
                } else {
                    r = y;
                    c = x;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}