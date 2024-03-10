import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int weightNumber = Integer.parseInt(in.readLine());

        int[] weights = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalWeight = Arrays.stream(weights).sum();

        int marbleNumber = Integer.parseInt(in.readLine());

        int[] marbles = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean[][] table = new boolean[weightNumber + 1][totalWeight + 1];

        table[0][0] = true;

        for (int index = 1; index <= weightNumber; index++) {
            for (int weight = 0; weight <= totalWeight; weight++) {
                table[index][weight] |= table[index - 1][weight];
                table[index][Math.abs(weight - weights[index - 1])] |= table[index - 1][weight];

                if (weight + weights[index - 1] <= totalWeight) {
                    table[index][weight + weights[index - 1]] |= table[index - 1][weight];
                }
            }
        }

        for (int marble : marbles) {
            out.write((marble <= totalWeight && table[weightNumber][marble]) ? ("Y ") : ("N "));
        }

        in.close();
        out.flush();
        out.close();
    }
}