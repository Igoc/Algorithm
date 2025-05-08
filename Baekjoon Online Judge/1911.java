import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Pool {
    public int start;
    public int end;

    public Pool(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        Pool[] pools = new Pool[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;

            pools[index] = new Pool(start, end);
        }

        Arrays.sort(pools, Comparator.comparingInt(o -> o.start));

        int lastPlankEnd = -1;
        int output = 0;

        for (Pool pool : pools) {
            if (lastPlankEnd < pool.start) {
                lastPlankEnd = pool.start + L - 1;
                output++;
            }

            if (lastPlankEnd < pool.end) {
                int plankNumber = (pool.end - lastPlankEnd + L - 1) / L;

                lastPlankEnd += plankNumber * L;
                output += plankNumber;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}