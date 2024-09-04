import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> subsequence = new ArrayList<>();
        int[] indexes = new int[N];

        Arrays.fill(indexes, -1);

        for (int element = 0; element < N; element++) {
            if (subsequence.isEmpty() || subsequence.get(subsequence.size() - 1) < A[element]) {
                subsequence.add(A[element]);
                indexes[element] = subsequence.size() - 1;
            } else {
                int index = Collections.binarySearch(subsequence, A[element]);

                if (index < 0) {
                    subsequence.set(-index - 1, A[element]);
                    indexes[element] = -index - 1;
                }
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int index = subsequence.size() - 1;

        for (int element = N - 1; element >= 0; element--) {
            if (index < 0) {
                break;
            }

            if (indexes[element] == index) {
                deque.push(A[element]);
                index--;
            }
        }

        out.write(subsequence.size() + "\n");

        while (!deque.isEmpty()) {
            out.write(deque.pop() + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}