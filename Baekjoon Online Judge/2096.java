import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] maximumScores = new int[N][3];
        int[][] minimumScores = new int[N][3];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            maximumScores[index][0] = Integer.parseInt(tokenizer.nextToken());
            maximumScores[index][1] = Integer.parseInt(tokenizer.nextToken());
            maximumScores[index][2] = Integer.parseInt(tokenizer.nextToken());

            minimumScores[index][0] = maximumScores[index][0];
            minimumScores[index][1] = maximumScores[index][1];
            minimumScores[index][2] = maximumScores[index][2];

            if (index > 0) {
                maximumScores[index][0] += Math.max(maximumScores[index - 1][0], maximumScores[index - 1][1]);
                maximumScores[index][1] += Math.max(maximumScores[index - 1][0], Math.max(maximumScores[index - 1][1], maximumScores[index - 1][2]));
                maximumScores[index][2] += Math.max(maximumScores[index - 1][1], maximumScores[index - 1][2]);

                minimumScores[index][0] += Math.min(minimumScores[index - 1][0], minimumScores[index - 1][1]);
                minimumScores[index][1] += Math.min(minimumScores[index - 1][0], Math.min(minimumScores[index - 1][1], minimumScores[index - 1][2]));
                minimumScores[index][2] += Math.min(minimumScores[index - 1][1], minimumScores[index - 1][2]);
            }
        }

        int maximumScore = Math.max(maximumScores[N - 1][0], Math.max(maximumScores[N - 1][1], maximumScores[N - 1][2]));
        int minimumScore = Math.min(minimumScores[N - 1][0], Math.min(minimumScores[N - 1][1], minimumScores[N - 1][2]));

        out.write(maximumScore + " " + minimumScore);

        in.close();
        out.flush();
        out.close();
    }
}