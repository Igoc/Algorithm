import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[] a = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        long activeArcaneStoneNumber = 1L;
        long output = 0L;

        for (int index = 1; index < n; index++) {
            output += a[index] * activeArcaneStoneNumber;

            if (activeArcaneStoneNumber < k) {
                activeArcaneStoneNumber++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}