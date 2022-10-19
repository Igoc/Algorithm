import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long calculateInfiniteSequence(long n, long p, long q, Map<Long, Long> sequence) {
        if (sequence.containsKey(n)) {
            return sequence.get(n);
        }

        long value = calculateInfiniteSequence(n / p, p, q, sequence) + calculateInfiniteSequence(n / q, p, q, sequence);

        sequence.put(n, value);

        return value;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        long N = Long.parseLong(tokenizer.nextToken());
        long P = Long.parseLong(tokenizer.nextToken());
        long Q = Long.parseLong(tokenizer.nextToken());

        Map<Long, Long> sequence = new HashMap<>();
        sequence.put(0L, 1L);

        out.write(String.valueOf(calculateInfiniteSequence(N, P, Q, sequence)));

        in.close();
        out.flush();
        out.close();
    }
}