import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] house = new int[N][N];

        for (int row = 0; row < N; row++) {
            house[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        long[][][] counts = new long[N][N][3];

        counts[0][1][0] = 1;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (col + 1 < N && house[row][col + 1] == 0) {
                    counts[row][col + 1][0] += counts[row][col][0] + counts[row][col][2];
                }

                if (row + 1 < N && house[row + 1][col] == 0) {
                    counts[row + 1][col][1] += counts[row][col][1] + counts[row][col][2];
                }

                if (col + 1 < N && row + 1 < N && house[row][col + 1] == 0 && house[row + 1][col] == 0 && house[row + 1][col + 1] == 0) {
                    counts[row + 1][col + 1][2] += counts[row][col][0] + counts[row][col][1] + counts[row][col][2];
                }
            }
        }

        long output = counts[N - 1][N - 1][0] + counts[N - 1][N - 1][1] + counts[N - 1][N - 1][2];

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}