import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] S = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(fruit -> fruit - 1)
                .toArray();

        int[] fruitNumbers = new int[9];
        int fruitTypeNumber = 0;

        int a = 0;
        int output = 0;

        for (int b = 0; b < N; b++) {
            fruitNumbers[S[b]]++;

            if (fruitNumbers[S[b]] == 1) {
                fruitTypeNumber++;
            }

            while (fruitTypeNumber > 2) {
                fruitNumbers[S[a]]--;

                if (fruitNumbers[S[a]] == 0) {
                    fruitTypeNumber--;
                }

                a++;
            }

            output = Math.max(output, b - a + 1);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}