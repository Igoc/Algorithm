import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int D = Integer.parseInt(tokenizer.nextToken());
        int P = Integer.parseInt(tokenizer.nextToken());

        List<Set<Integer>> numbers = new ArrayList<>();
        int maximumNumber = (int) Math.pow(10, D) - 1;

        numbers.add(Set.of(1));

        for (int operation = 1; operation <= P; operation++) {
            numbers.add(new HashSet<>());

            for (int number : numbers.get(operation - 1)) {
                for (int multiplier = 2; multiplier <= 9; multiplier++) {
                    if ((long) number * (long) multiplier <= maximumNumber) {
                        numbers.get(operation).add(number * multiplier);
                    }
                }
            }
        }

        int output = -1;

        for (int number : numbers.get(P)) {
            output = Math.max(output, number);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}