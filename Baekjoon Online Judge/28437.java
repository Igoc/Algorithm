import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] sticks = new int[100001];

        Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(A -> sticks[A]++);

        for (int index = 1; index < 100001; index++) {
            int multiplier = 2;

            while (index * multiplier < 100001) {
                sticks[index * multiplier] += sticks[index];
                multiplier++;
            }
        }

        int Q = Integer.parseInt(in.readLine());

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        for (int index = 0; index < Q; index++) {
            out.write(sticks[Integer.parseInt(tokenizer.nextToken())] + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}