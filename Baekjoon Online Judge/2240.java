import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int T = Integer.parseInt(tokenizer.nextToken());
        int W = Math.min(Integer.parseInt(tokenizer.nextToken()), T);

        int[][] plumNumbers = new int[W + 1][2];

        for (int time = 1; time <= T; time++) {
            int tree = Integer.parseInt(in.readLine());

            for (int move = Math.min(time, W); move > 0; move--) {
                plumNumbers[move][0] = Math.max(plumNumbers[move][0], plumNumbers[move - 1][1]);
                plumNumbers[move][1] = Math.max(plumNumbers[move][1], plumNumbers[move - 1][0]);

                if (move > 1 || tree == 2) {
                    plumNumbers[move][tree - 1]++;
                }
            }

            if (tree == 1) {
                plumNumbers[0][0]++;
            }
        }

        int output = 0;

        for (int move = 0; move <= W; move++) {
            output = Math.max(output, Math.max(plumNumbers[move][0], plumNumbers[move][1]));
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}