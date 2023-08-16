import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void showSequence(int n, int m, int[] sequence, int depth) throws IOException {
        if (depth >= m) {
            for (int number : sequence) {
                out.write(number + " ");
            }

            out.write('\n');

            return;
        }

        for (int number = (depth == 0) ? (1) : (sequence[depth - 1]); number <= n; number++) {
            sequence[depth] = number;
            showSequence(n, m, sequence, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        showSequence(N, M, new int[M], 0);

        in.close();
        out.flush();
        out.close();
    }
}