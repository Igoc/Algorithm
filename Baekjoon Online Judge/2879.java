import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] initialTabNumbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] targetTabNumbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int previousTabNumber = 0;
        int output = 0;

        for (int line = 0; line < N; line++) {
            int requiredTabNumber = targetTabNumbers[line] - initialTabNumbers[line];

            if (requiredTabNumber < 0) {
                if (previousTabNumber > 0) {
                    output -= requiredTabNumber;
                } else if (requiredTabNumber < previousTabNumber) {
                    output -= requiredTabNumber - previousTabNumber;
                }
            } else {
                if (previousTabNumber < 0) {
                    output += requiredTabNumber;
                } else if (requiredTabNumber > previousTabNumber) {
                    output += requiredTabNumber - previousTabNumber;
                }
            }

            previousTabNumber = requiredTabNumber;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}