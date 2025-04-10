import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int L = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        int[] cutPoints = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .distinct()
                .sorted()
                .toArray();

        int minLength = Math.max(cutPoints[0], L - cutPoints[cutPoints.length - 1]);
        int maxLength = L;

        for (int index = 1; index < cutPoints.length; index++) {
            minLength = Math.max(minLength, cutPoints[index] - cutPoints[index - 1]);
        }

        while (minLength <= maxLength) {
            int length = (minLength + maxLength) >> 1;
            int firstCutPoint = findFirstCutPoint(cutPoints, length, L, C);

            if (firstCutPoint == -1) {
                minLength = length + 1;
            } else {
                maxLength = length - 1;
            }
        }

        out.write(minLength + " " + findFirstCutPoint(cutPoints, minLength, L, C));

        in.close();
        out.flush();
        out.close();
    }

    public static int findFirstCutPoint(int[] cutPoints, int longestPieceLength, int logLength, int allowedCutNumber) {
        int lastCutPoint = logLength;
        int cutCount = 0;

        for (int index = cutPoints.length - 2; index >= 0; index--) {
            if (lastCutPoint - cutPoints[index] > longestPieceLength) {
                lastCutPoint = cutPoints[index + 1];
                cutCount++;
            }
        }

        if (lastCutPoint > longestPieceLength || cutCount < allowedCutNumber) {
            lastCutPoint = cutPoints[0];
            cutCount++;
        }

        if (cutCount > allowedCutNumber) {
            return -1;
        }

        return lastCutPoint;
    }
}