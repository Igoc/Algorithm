import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Set<Integer> sum = new HashSet<>();
        int output = 0;

        for (int target = 0; target < N; target++) {
            for (int index = 0; index < target; index++) {
                sum.add(A[index] + A[target - 1]);
            }

            for (int index = 0; index < target; index++) {
                if (A[index] * 3 == A[target] || sum.contains(A[target] - A[index])) {
                    output++;
                    break;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}