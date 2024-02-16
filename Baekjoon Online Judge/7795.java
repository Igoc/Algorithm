import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            int[] A = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            int[] B = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            int bIndex = 0;
            int output = 0;

            for (int aIndex = 0; aIndex < N; aIndex++) {
                while (bIndex < M && B[bIndex] < A[aIndex]) {
                    bIndex++;
                }

                output += bIndex;
            }

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}