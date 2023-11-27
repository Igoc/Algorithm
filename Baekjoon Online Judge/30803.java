import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean[] open = new boolean[N];

        Arrays.fill(open, true);

        int Q = Integer.parseInt(in.readLine());

        long output = 0L;

        for (int faucet : A) {
            output += faucet;
        }

        out.write(output + "\n");

        for (int index = 0; index < Q; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int type = Integer.parseInt(tokenizer.nextToken());
            int i = Integer.parseInt(tokenizer.nextToken()) - 1;

            if (type == 1) {
                int x = Integer.parseInt(tokenizer.nextToken());

                if (open[i]) {
                    output -= A[i] - x;
                }

                A[i] = x;
            } else {
                open[i] = !open[i];

                if (open[i]) {
                    output += A[i];
                } else {
                    output -= A[i];
                }
            }

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}