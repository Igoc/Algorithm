import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void showSequence(int[] numbers, int lastIndex, int m, int[] sequence, int depth) throws IOException {
        if (depth >= m) {
            for (int number : sequence) {
                out.write(number + " ");
            }

            out.write('\n');

            return;
        }

        for (int index = lastIndex; index < numbers.length; index++) {
            sequence[depth] = numbers[index];
            showSequence(numbers, index, m, sequence, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        showSequence(numbers, 0, M, new int[M], 0);

        in.close();
        out.flush();
        out.close();
    }
}