import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] testPapers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int minimumScore = Integer.MAX_VALUE;
        int maximumScore = 0;

        for (int x : testPapers) {
            minimumScore = Math.min(minimumScore, x);
            maximumScore += x;
        }

        while (minimumScore <= maximumScore) {
            int score = (minimumScore + maximumScore) / 2;

            int groupScore = 0;
            int groupNumber = 0;

            for (int x : testPapers) {
                groupScore += x;

                if (groupScore >= score) {
                    groupScore = 0;
                    groupNumber++;
                }
            }

            if (groupNumber >= K) {
                minimumScore = score + 1;
            } else {
                maximumScore = score - 1;
            }
        }

        out.write(String.valueOf(maximumScore));

        in.close();
        out.flush();
        out.close();
    }
}