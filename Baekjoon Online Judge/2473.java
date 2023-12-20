import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        long[] solutions = Arrays.stream(in.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();

        long minimumValue = Long.MAX_VALUE;
        long[] outputs = new long[3];

        for (int first = 0; first < N; first++) {
            for (int second = first + 2; second < N; second++) {
                long value = solutions[first] + solutions[second];

                int leftIndex = first + 1;
                int rightIndex = second - 1;

                while (leftIndex <= rightIndex) {
                    int midIndex = (leftIndex + rightIndex) >> 1;

                    if (value + solutions[midIndex] < 0) {
                        leftIndex = midIndex + 1;
                    } else {
                        rightIndex = midIndex - 1;
                    }
                }

                if (leftIndex < second && Math.abs(value + solutions[leftIndex]) < minimumValue) {
                    minimumValue = Math.abs(value + solutions[leftIndex]);

                    outputs[0] = solutions[first];
                    outputs[1] = solutions[leftIndex];
                    outputs[2] = solutions[second];
                }

                if (rightIndex > first && Math.abs(value + solutions[rightIndex]) < minimumValue) {
                    minimumValue = Math.abs(value + solutions[rightIndex]);

                    outputs[0] = solutions[first];
                    outputs[1] = solutions[rightIndex];
                    outputs[2] = solutions[second];
                }
            }
        }

        out.write(outputs[0] + " " + outputs[1] + " " + outputs[2]);

        in.close();
        out.flush();
        out.close();
    }
}