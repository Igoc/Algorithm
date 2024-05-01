import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int S = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        out.write(String.valueOf(calculatePartialSequenceNumber(numbers, 0, 0, S, 0, 0)));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculatePartialSequenceNumber(int[] numbers, int length, int sum, int target, int index, int partialSequenceNumber) {
        if (index == numbers.length) {
            if (length > 0 && sum == target) {
                return partialSequenceNumber + 1;
            }

            return partialSequenceNumber;
        }

        partialSequenceNumber = calculatePartialSequenceNumber(numbers, length, sum, target, index + 1, partialSequenceNumber);
        partialSequenceNumber = calculatePartialSequenceNumber(numbers, length + 1, sum + numbers[index], target, index + 1, partialSequenceNumber);

        return partialSequenceNumber;
    }
}