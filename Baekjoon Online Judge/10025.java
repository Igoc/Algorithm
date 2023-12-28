import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] buckets = new int[1000001];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int g = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());

            buckets[x] = g;
        }

        for (int position = 1; position < buckets.length; position++) {
            buckets[position] += buckets[position - 1];
        }

        int output = 0;

        for (int position = 0; position < buckets.length; position++) {
            int sum = buckets[Math.min(position + K, buckets.length - 1)];

            if (position - K > 0) {
                sum -= buckets[position - K - 1];
            }

            output = Math.max(output, sum);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}