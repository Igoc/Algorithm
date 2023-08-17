import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int minimumNumber = Integer.MAX_VALUE;
        int maximumNumber = 0;

        for (int number : numbers) {
            minimumNumber = Math.min(minimumNumber, number);
            maximumNumber = Math.max(maximumNumber, number);
        }

        int minimumSectionScore = 0;
        int maximumSectionScore = maximumNumber - minimumNumber;

        while (minimumSectionScore <= maximumSectionScore) {
            int sectionScore = (minimumSectionScore + maximumSectionScore) / 2;
            int sectionNumber = 1;

            minimumNumber = Integer.MAX_VALUE;
            maximumNumber = 0;

            for (int number : numbers) {
                minimumNumber = Math.min(minimumNumber, number);
                maximumNumber = Math.max(maximumNumber, number);

                if (maximumNumber - minimumNumber > sectionScore) {
                    minimumNumber = number;
                    maximumNumber = number;
                    sectionNumber++;
                }
            }

            if (sectionNumber > M) {
                minimumSectionScore = sectionScore + 1;
            } else {
                maximumSectionScore = sectionScore - 1;
            }
        }

        out.write(String.valueOf(minimumSectionScore));

        in.close();
        out.flush();
        out.close();
    }
}