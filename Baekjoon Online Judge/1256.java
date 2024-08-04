import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[][] dictionary = new int[N + 1][M + 1];

        for (int row = 1; row <= N; row++) {
            dictionary[row][0] = 1;
        }

        for (int col = 1; col <= M; col++) {
            dictionary[0][col] = 1;
        }

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                dictionary[row][col] = dictionary[row - 1][col] + dictionary[row][col - 1];

                if (dictionary[row][col] > K) {
                    dictionary[row][col] = K;
                }
            }
        }

        StringBuilder output = new StringBuilder();

        if (dictionary[N][M] < K) {
            output.append(-1);
        } else {
            while (N > 0 && M > 0) {
                if (dictionary[N - 1][M] >= K) {
                    output.append('a');
                    N--;
                } else {
                    output.append('z');
                    K -= dictionary[N - 1][M];
                    M--;
                }
            }

            while (N > 0) {
                output.append('a');
                N--;
            }

            while (M > 0) {
                output.append('z');
                M--;
            }
        }

        out.write(output.toString());

        in.close();
        out.flush();
        out.close();
    }
}