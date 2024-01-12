import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sum = new int[N];
        int output = 0;

        System.arraycopy(A, 0, sum, 0, N);

        for (int rightIndex = 0; rightIndex < N; rightIndex++) {
            for (int leftIndex = 0; leftIndex < rightIndex; leftIndex++) {
                if (A[leftIndex] < A[rightIndex]) {
                    sum[rightIndex] = Math.max(sum[rightIndex], sum[leftIndex] + A[rightIndex]);
                }
            }

            output = Math.max(output, sum[rightIndex]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}