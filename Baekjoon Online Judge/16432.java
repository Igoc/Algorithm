import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] riceCakes = Arrays.stream(in.readLine().split(" "))
                .skip(1L)
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean[][] possible = new boolean[N][10];

        for (int riceCake : riceCakes) {
            possible[0][riceCake] = true;
        }

        for (int date = 1; date < N; date++) {
            riceCakes = Arrays.stream(in.readLine().split(" "))
                    .skip(1L)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int riceCake : riceCakes) {
                for (int type = 1; type <= 9; type++) {
                    if (possible[date - 1][type] && type != riceCake) {
                        possible[date][riceCake] = true;
                        break;
                    }
                }
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int date = N - 1; date >= 0; date--) {
            for (int type = 1; type <= 9; type++) {
                if (possible[date][type] && (deque.isEmpty() || type != deque.getLast())) {
                    deque.offerLast(type);
                    break;
                }
            }
        }

        if (deque.size() != N) {
            deque.clear();
            deque.offerLast(-1);
        }

        while (!deque.isEmpty()) {
            out.write(deque.pollLast() + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}