import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] heights = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        double[][] gradients = new double[N][N];

        for (int origin = 0; origin < N; origin++) {
            for (int destination = 0; destination < N; destination++) {
                int x = destination - origin;
                int y = heights[destination] - heights[origin];
                int gcd = calculateGcd(x, y);

                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }

                gradients[origin][destination] = (double) y / (double) x;
            }
        }

        int[] counts = new int[N];

        for (int left = 0; left < N; left++) {
            double maxGradient = -Double.MAX_VALUE;

            for (int right = left + 1; right < N; right++) {
                if (gradients[left][right] > maxGradient) {
                    maxGradient = gradients[left][right];
                    counts[left]++;
                    counts[right]++;
                }
            }
        }

        int output = 0;

        for (int count : counts) {
            output = Math.max(output, count);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateGcd(int a, int b) {
        while (b != 0) {
            int r = a % b;

            a = b;
            b = r;
        }

        return Math.abs(a);
    }
}