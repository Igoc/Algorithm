import java.io.*;
import java.util.Arrays;

public class Main {
    static final int MODULO_NUMBER = 10007;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] numbers = new int[10];

        Arrays.fill(numbers, 1);

        for (int length = 2; length <= N; length++) {
            for (int digit = 8; digit >= 0; digit--) {
                numbers[digit] = (numbers[digit] + numbers[digit + 1]) % MODULO_NUMBER;
            }
        }

        int output = 0;

        for (int number : numbers) {
            output += number;
        }

        out.write(String.valueOf(output % MODULO_NUMBER));

        in.close();
        out.flush();
        out.close();
    }
}