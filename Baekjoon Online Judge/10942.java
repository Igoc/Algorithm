import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] numbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] palindrome = determinePalindrome(numbers);

        int M = Integer.parseInt(in.readLine());

        for (int query = 0; query < M; query++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int S = Integer.parseInt(tokenizer.nextToken()) - 1;
            int E = Integer.parseInt(tokenizer.nextToken()) - 1;

            out.write(palindrome[E - S][S] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }

    public static int[][] determinePalindrome(int[] numbers) {
        int[][] palindrome = new int[numbers.length][numbers.length];

        for (int index = 0; index < numbers.length; index++) {
            palindrome[0][index] = 1;

            if (index < numbers.length - 1 && numbers[index] == numbers[index + 1]) {
                palindrome[1][index] = 1;
            }
        }

        for (int size = 0; size < palindrome.length - 2; size++) {
            for (int index = 1; index < numbers.length - size - 1; index++) {
                if (palindrome[size][index] == 0) {
                    continue;
                }

                if (numbers[index - 1] == numbers[index + size + 1]) {
                    palindrome[size + 2][index - 1] = 1;
                }
            }
        }

        return palindrome;
    }
}