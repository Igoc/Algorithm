import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int S = Integer.parseInt(tokenizer.nextToken());

        int[] sequence = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int leftIndex = 0;
        int sum = 0;
        int minimumLength = Integer.MAX_VALUE;

        for (int rightIndex = 0; rightIndex < N; rightIndex++) {
            sum += sequence[rightIndex];

            while (sum - sequence[leftIndex] >= S) {
                sum -= sequence[leftIndex++];
            }

            if (sum >= S) {
                minimumLength = Math.min(minimumLength, rightIndex - leftIndex + 1);
            }
        }

        if (minimumLength == Integer.MAX_VALUE) {
            minimumLength = 0;
        }

        out.write(String.valueOf(minimumLength));

        in.close();
        out.flush();
        out.close();
    }
}