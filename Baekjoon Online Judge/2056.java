import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] completionTimes = new int[N];
        int output = 0;

        for (int task = 0; task < N; task++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int time = Integer.parseInt(tokenizer.nextToken());
            int priorTaskNumber = Integer.parseInt(tokenizer.nextToken());

            while (priorTaskNumber-- > 0) {
                completionTimes[task] = Math.max(completionTimes[task], completionTimes[Integer.parseInt(tokenizer.nextToken()) - 1]);
            }

            completionTimes[task] += time;
            output = Math.max(output, completionTimes[task]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}