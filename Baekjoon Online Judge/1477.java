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
        int L = Integer.parseInt(tokenizer.nextToken());

        int[] distances = new int[N + 1];

        distances[0] = L;

        if (N != 0) {
            int[] restAreas = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            distances[0] = restAreas[0];
            distances[N] = L - restAreas[N - 1];

            for (int index = 0; index < N - 1; index++) {
                distances[index + 1] = restAreas[index + 1] - restAreas[index];
            }
        }

        int minimumInterval = 1;
        int maximumInterval = L - 1;

        while (minimumInterval <= maximumInterval) {
            int interval = (minimumInterval + maximumInterval) / 2;
            int restAreaNumber = 0;

            for (int distance : distances) {
                restAreaNumber += distance / interval;

                if (distance % interval == 0) {
                    restAreaNumber--;
                }
            }

            if (restAreaNumber > M) {
                minimumInterval = interval + 1;
            } else {
                maximumInterval = interval - 1;
            }
        }

        out.write(String.valueOf(minimumInterval));

        in.close();
        out.flush();
        out.close();
    }
}