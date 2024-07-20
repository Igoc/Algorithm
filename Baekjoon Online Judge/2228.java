import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MIN_VALUE = Short.MIN_VALUE * 100 - 1;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = new int[N];

        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(in.readLine());
        }

        int[][] groups = new int[M + 1][N + 2];

        Arrays.stream(groups)
                .forEach(group -> Arrays.fill(group, MIN_VALUE));

        Arrays.fill(groups[0], 0);

        for (int group = 1; group < M + 1; group++) {
            for (int index = group * 2; index < N + 2; index++) {
                groups[group][index] = groups[group][index - 1];

                for (int offset = 2; offset <= index; offset++) {
                    groups[group][index] = Math.max(groups[group][index], groups[group - 1][index - offset]);
                }

                groups[group][index] += numbers[index - 2];
            }
        }

        int output = MIN_VALUE;

        for (int index = 0; index < N + 2; index++) {
            output = Math.max(output, groups[M][index]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}