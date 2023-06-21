import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] difficultyOpinions = new int[n];

        for (int index = 0; index < n; index++) {
            difficultyOpinions[index] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(difficultyOpinions);

        int truncatedNumber = (int) Math.round(n * 0.15);
        int output = 0;

        for (int index = truncatedNumber; index < n - truncatedNumber; index++) {
            output += difficultyOpinions[index];
        }

        out.write(String.valueOf(Math.round(output / (n - truncatedNumber * 2.0))));

        in.close();
        out.flush();
        out.close();
    }
}