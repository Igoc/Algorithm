import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] H = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int output = Integer.MAX_VALUE;

        for (int elzaTop = 0; elzaTop < N; elzaTop++) {
            for (int elzaBottom = elzaTop + 3; elzaBottom < N; elzaBottom++) {
                int elzaSize = H[elzaTop] + H[elzaBottom];
                int annaTop = elzaTop + 1;
                int annaBottom = elzaBottom - 1;

                while (annaTop < elzaBottom - 1 && annaBottom > elzaTop + 1 && H[annaTop] <= H[annaBottom]) {
                    int annaSize = H[annaTop] + H[annaBottom];

                    if (annaSize <= elzaSize) {
                        annaTop++;
                    } else {
                        annaBottom--;
                    }

                    output = Math.min(output, Math.abs(elzaSize - annaSize));
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}