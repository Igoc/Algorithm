import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        int output = 0;

        for (int index = 0; index < n; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            while (!deque.isEmpty() && deque.peekLast() > y) {
                deque.pollLast();
            }

            if (y == 0) {
                continue;
            }

            if (deque.isEmpty() || deque.peekLast() < y) {
                deque.offerLast(y);
                output++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}