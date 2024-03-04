import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        long A = Long.parseLong(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(in.readLine());

        long C = Long.parseLong(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        int K = Integer.parseInt(in.readLine());

        long tokaMinimumMoveNumber = 1L;
        long tokaMaximumMoveNumber = (long) Math.ceil((double) B / (double) K);

        while (tokaMinimumMoveNumber <= tokaMaximumMoveNumber) {
            long tokaMoveNumber = (tokaMinimumMoveNumber + tokaMaximumMoveNumber) / 2;
            long tokaMoveDistance = (long) (tokaMoveNumber * (B - (tokaMoveNumber - 1) * K / 2.0));

            if (tokaMoveDistance < A) {
                tokaMinimumMoveNumber = tokaMoveNumber + 1;
            } else {
                tokaMaximumMoveNumber = tokaMoveNumber - 1;
            }
        }

        long doldoliMinimumMoveNumber = 1L;
        long doldoliMaximumMoveNumber = (long) Math.ceil((double) (A + C) / (double) D);

        while (doldoliMinimumMoveNumber <= doldoliMaximumMoveNumber) {
            long doldoliMoveNumber = (doldoliMinimumMoveNumber + doldoliMaximumMoveNumber) / 2;
            long doldoliMoveDistance = doldoliMoveNumber * D;

            if (doldoliMoveDistance < A + C) {
                doldoliMinimumMoveNumber = doldoliMoveNumber + 1;
            } else {
                doldoliMaximumMoveNumber = doldoliMoveNumber - 1;
            }
        }

        long tokaMoveNumber = tokaMinimumMoveNumber;
        long doldoliMoveNumber = doldoliMinimumMoveNumber;

        if (tokaMoveNumber <= (long) Math.ceil((double) B / (double) K) && tokaMoveNumber < doldoliMoveNumber) {
            out.write(String.valueOf(tokaMoveNumber));
        } else {
            out.write("-1");
        }

        in.close();
        out.flush();
        out.close();
    }
}