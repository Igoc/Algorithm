import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(in.readLine());

            int[][] stickers = new int[2][n];

            stickers[0] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            stickers[1] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[][] scores = new int[2][n];

            scores[0][0] = stickers[0][0];
            scores[1][0] = stickers[1][0];

            if (n > 1) {
                scores[0][1] = scores[1][0] + stickers[0][1];
                scores[1][1] = scores[0][0] + stickers[1][1];
            }

            for (int col = 2; col < n; col++) {
                scores[0][col] = Math.max(scores[1][col - 1], scores[1][col - 2]) + stickers[0][col];
                scores[1][col] = Math.max(scores[0][col - 1], scores[0][col - 2]) + stickers[1][col];
            }

            out.write(Math.max(scores[0][n - 1], scores[1][n - 1]) + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}