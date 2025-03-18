import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        int[] shootingStations = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int output = 0;

        for (int animal = 0; animal < N; animal++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            int left = 0;
            int right = M - 1;

            while (left <= right) {
                int index = (left + right) >> 1;

                if (x - L + y <= shootingStations[index] && shootingStations[index] <= x + L - y) {
                    output++;
                    break;
                }

                if (shootingStations[index] < x - L + y) {
                    left = index + 1;
                } else if (shootingStations[index] > x + L - y) {
                    right = index - 1;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}