import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        boolean[] compositeNumber = new boolean[N + 1];

        for (int number = 2; number <= N; number++) {
            if (!compositeNumber[number]) {
                int offset = 2;

                while (number * offset <= N) {
                    compositeNumber[number * offset] = true;
                    offset++;
                }

                if (number >= M) {
                    out.write(String.valueOf(number) + '\n');
                }
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}