import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] uniqueNumber = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = 0;

        for (int number : uniqueNumber) {
            sum += number * number;
        }

        out.write(String.valueOf(sum % 10));

        in.close();
        out.flush();
        out.close();
    }
}