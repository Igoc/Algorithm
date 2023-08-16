import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            boolean[] K = {false, true, false, false, false, false, false};

            int N = Integer.parseInt(in.readLine());

            for (int turn = 0; turn < N; turn++) {
                boolean[] result = new boolean[7];

                StringTokenizer tokenizer = new StringTokenizer(in.readLine());

                char op1 = tokenizer.nextToken().charAt(0);
                int v1 = Integer.parseInt(tokenizer.nextToken());
                char op2 = tokenizer.nextToken().charAt(0);
                int v2 = Integer.parseInt(tokenizer.nextToken());

                for (int index = 0; index < 7; index++) {
                    if (K[index]) {
                        if (op1 == '+') {
                            result[(index + v1) % 7] = true;
                        } else {
                            result[(index * v1) % 7] = true;
                        }

                        if (op2 == '+') {
                            result[(index + v2) % 7] = true;
                        } else {
                            result[(index * v2) % 7] = true;
                        }
                    }
                }

                K = result;
            }

            out.write((K[0]) ? ("LUCKY\n") : ("UNLUCKY\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}