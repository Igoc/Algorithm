import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        int[] a = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        long[] cumulativePopularity = new long[N];
        long[] cumulativeSquarePopularity = new long[N];

        for (int index = 0; index < N; index++) {
            cumulativePopularity[index] = cumulativeSquarePopularity[index] = a[index];
            cumulativeSquarePopularity[index] *= cumulativeSquarePopularity[index];

            if (index > 0) {
                cumulativePopularity[index] += cumulativePopularity[index - 1];
                cumulativeSquarePopularity[index] += cumulativeSquarePopularity[index - 1];
            }
        }

        for (int division = 0; division < Q; division++) {
            tokenizer = new StringTokenizer(in.readLine());

            int l = Integer.parseInt(tokenizer.nextToken()) - 1;
            int r = Integer.parseInt(tokenizer.nextToken()) - 1;

            long currentCumulativePopularity = cumulativePopularity[r];
            long currentCumulativeSquarePopularity = cumulativeSquarePopularity[r];

            if (l > 0) {
                currentCumulativePopularity -= cumulativePopularity[l - 1];
                currentCumulativeSquarePopularity -= cumulativeSquarePopularity[l - 1];
            }

            long output = (currentCumulativePopularity * currentCumulativePopularity - currentCumulativeSquarePopularity) / 2;

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}