import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(in.readLine());

        List<Integer> numbers = new ArrayList<>();

        for (int index = 0; index < K; index++) {
            int number = Integer.parseInt(in.readLine());

            if (number == 0) {
                numbers.remove(numbers.size() - 1);
            } else {
                numbers.add(number);
            }
        }

        int output = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}