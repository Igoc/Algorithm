import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] heights = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] visibleBuildingNumbers = new int[N];
        int[] closestVisibleBuilding = new int[N];

        Arrays.fill(closestVisibleBuilding, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();

        for (int index = 0; index < N; index++) {
            while (!deque.isEmpty() && heights[deque.peekLast()] <= heights[index]) {
                deque.pollLast();
            }

            visibleBuildingNumbers[index] += deque.size();

            if (!deque.isEmpty() && Math.abs(deque.peekLast() - index) < Math.abs(closestVisibleBuilding[index] - index)) {
                closestVisibleBuilding[index] = deque.peekLast();
            }

            deque.offerLast(index);
        }

        deque.clear();

        for (int index = N - 1; index >= 0; index--) {
            while (!deque.isEmpty() && heights[deque.peekLast()] <= heights[index]) {
                deque.pollLast();
            }

            visibleBuildingNumbers[index] += deque.size();

            if (!deque.isEmpty() && Math.abs(deque.peekLast() - index) < Math.abs(closestVisibleBuilding[index] - index)) {
                closestVisibleBuilding[index] = deque.peekLast();
            }

            deque.offerLast(index);
        }

        for (int index = 0; index < N; index++) {
            out.write(String.valueOf(visibleBuildingNumbers[index]));

            if (visibleBuildingNumbers[index] > 0) {
                out.write(" " + (closestVisibleBuilding[index] + 1));
            }

            out.newLine();
        }

        in.close();
        out.flush();
        out.close();
    }
}