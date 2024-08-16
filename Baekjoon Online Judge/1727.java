import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        int[] men = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int[] women = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        if (n < m) {
            out.write(String.valueOf(calculateMinimumDifference(men, women)));
        } else {
            out.write(String.valueOf(calculateMinimumDifference(women, men)));
        }

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateMinimumDifference(int[] smallGroup, int[] largeGroup) {
        int[][] differences = new int[smallGroup.length + 1][largeGroup.length + 1];

        for (int row = 1; row <= smallGroup.length; row++) {
            for (int col = 0; col <= largeGroup.length; col++) {
                differences[row][col] = Integer.MAX_VALUE;
            }
        }

        for (int row = 1; row <= smallGroup.length; row++) {
            for (int col = row; col <= largeGroup.length; col++) {
                differences[row][col] = Math.min(differences[row][col - 1], differences[row - 1][col - 1] + Math.abs(smallGroup[row - 1] - largeGroup[col - 1]));
            }
        }

        return differences[smallGroup.length][largeGroup.length];
    }
}