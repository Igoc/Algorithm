import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        long[] A = Arrays.stream(in.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        for (int index = 1; index < N; index++) {
            A[index] += A[index - 1];
        }

        long[] counts = new long[M];

        for (int index = 0; index < N; index++) {
            counts[(int) (A[index] % M)]++;
        }

        long output = counts[0];

        for (long count : counts) {
            output += count * (count - 1) >> 1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}