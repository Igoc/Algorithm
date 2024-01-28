import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] solutions = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int leftIndex = 0;
        int rightIndex = N - 1;
        int leftSolution = solutions[0];
        int rightSolution = solutions[N - 1];

        while (leftIndex < rightIndex) {
            if (Math.abs(solutions[leftIndex] + solutions[rightIndex]) < Math.abs(leftSolution + rightSolution)) {
                leftSolution = solutions[leftIndex];
                rightSolution = solutions[rightIndex];
            }

            if (solutions[leftIndex] + solutions[rightIndex] < 0) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        out.write(leftSolution + " " + rightSolution);

        in.close();
        out.flush();
        out.close();
    }
}