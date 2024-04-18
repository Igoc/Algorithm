import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int output = 0;

        loop:
        for (int index = 0; index < N; index++) {
            for (int left = 0; left < N; left++) {
                int minimumIndex = 0;
                int maximumIndex = N - 1;

                while (minimumIndex <= maximumIndex) {
                    int right = (minimumIndex + maximumIndex) / 2;

                    if (left != right && left != index && right != index && A[left] + A[right] == A[index]) {
                        output++;

                        continue loop;
                    }

                    if (A[left] + A[right] < A[index]) {
                        minimumIndex = right + 1;
                    } else {
                        maximumIndex = right - 1;
                    }
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}