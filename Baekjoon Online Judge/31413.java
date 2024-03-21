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

        int[] s = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        tokenizer = new StringTokenizer(in.readLine());

        int A = Integer.parseInt(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        int[][] table = new int[N + 1][N + 1];

        for (int date = 0; date < N; date++) {
            for (int bloodDonation = 0; bloodDonation <= N; bloodDonation++) {
                if (date - bloodDonation * D < 0) {
                    break;
                }

                table[date + 1][bloodDonation] = Math.max(table[date + 1][bloodDonation], table[date][bloodDonation] + s[date]);

                if (bloodDonation < N) {
                    table[Math.min(date + D, N)][bloodDonation + 1] = Math.max(table[Math.min(date + D, N)][bloodDonation + 1], table[date][bloodDonation] + A);
                }
            }
        }

        int output = -1;

        for (int bloodDonation = 0; bloodDonation <= N; bloodDonation++) {
            if (table[N][bloodDonation] >= M) {
                output = bloodDonation;

                break;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}