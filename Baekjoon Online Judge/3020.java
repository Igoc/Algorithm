import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int H = Integer.parseInt(tokenizer.nextToken());

        int[] obstacleNumbers = new int[H];

        for (int x = 0; x < N; x++) {
            int size = Integer.parseInt(in.readLine());

            if (x % 2 == 0) {
                obstacleNumbers[0]++;
                obstacleNumbers[size]--;
            } else {
                obstacleNumbers[H - size]++;
            }
        }

        int obstacleMinimumNumber = obstacleNumbers[0];

        for (int y = 1; y < H; y++) {
            obstacleNumbers[y] += obstacleNumbers[y - 1];
            obstacleMinimumNumber = Math.min(obstacleMinimumNumber, obstacleNumbers[y]);
        }

        int sectionNumber = 0;

        for (int y = 0; y < H; y++) {
            if (obstacleNumbers[y] == obstacleMinimumNumber) {
                sectionNumber++;
            }
        }

        out.write(obstacleMinimumNumber + " " + sectionNumber);

        in.close();
        out.flush();
        out.close();
    }
}