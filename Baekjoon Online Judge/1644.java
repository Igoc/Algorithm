import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Integer> primeNumbers = new ArrayList<>();
        boolean[] compositeNumber = new boolean[N + 1];

        compositeNumber[0] = true;
        compositeNumber[1] = true;

        for (int number = 2; number <= N; number++) {
            if (compositeNumber[number]) {
                continue;
            }

            primeNumbers.add(number);

            for (int multiplier = 2; number * multiplier <= N; multiplier++) {
                compositeNumber[number * multiplier] = true;
            }
        }

        int leftIndex = 0;
        int sum = 0;
        int output = 0;

        for (int rightIndex = 0; rightIndex < primeNumbers.size(); rightIndex++) {
            sum += primeNumbers.get(rightIndex);

            while (leftIndex <= rightIndex && sum > N) {
                sum -= primeNumbers.get(leftIndex++);
            }

            if (sum == N) {
                output++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}