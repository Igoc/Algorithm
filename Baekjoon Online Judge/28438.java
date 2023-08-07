import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        int[] rowValues = new int[N];
        int[] columnValues = new int[M];

        for (int operation = 0; operation < Q; operation++) {
            tokenizer = new StringTokenizer(in.readLine());

            int operator = Integer.parseInt(tokenizer.nextToken());
            int index = Integer.parseInt(tokenizer.nextToken()) - 1;
            int value = Integer.parseInt(tokenizer.nextToken());

            if (operator == 1) {
                rowValues[index] += value;
            } else {
                columnValues[index] += value;
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                out.write(rowValues[row] + columnValues[col] + " ");
            }

            out.write("\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}