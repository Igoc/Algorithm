import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] ranges = new int[N][];

        for (int index = 0; index < N; index++) {
            ranges[index] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        long minimumInteger = 1L;
        long maximumInteger = Integer.MAX_VALUE;

        while (minimumInteger <= maximumInteger) {
            long integer = (minimumInteger + maximumInteger) / 2;
            long count = 0L;

            for (int[] range : ranges) {
                long discriminant = Math.min(integer, range[1]) - range[0];

                if (discriminant >= 0) {
                    count += discriminant / range[2] + 1;
                }
            }

            if (count % 2 == 0) {
                minimumInteger = integer + 1;
            } else {
                maximumInteger = integer - 1;
            }
        }

        long[] counts = {0L, 0L};

        for (int[] range : ranges) {
            long discriminant = Math.min(minimumInteger - 1, range[1]) - range[0];

            if (discriminant >= 0) {
                counts[0] += discriminant / range[2] + 1;
            }

            discriminant = Math.min(minimumInteger, range[1]) - range[0];

            if (discriminant >= 0) {
                counts[1] += discriminant / range[2] + 1;
            }
        }

        if ((counts[1] - counts[0]) % 2 == 0) {
            out.write("NOTHING");
        } else {
            out.write(minimumInteger + " " + (counts[1] - counts[0]));
        }

        in.close();
        out.flush();
        out.close();
    }
}