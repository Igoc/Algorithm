import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Integer> A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        int initialMaximumValue = A.get(N - 1);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(A);
        int maximumValue = initialMaximumValue;
        int output = Integer.MAX_VALUE;

        while (priorityQueue.peek() != initialMaximumValue) {
            output = Math.min(output, maximumValue - priorityQueue.peek());
            maximumValue = Math.max(maximumValue, priorityQueue.peek() * 2);
            priorityQueue.offer(priorityQueue.poll() * 2);
        }

        output = Math.min(output, maximumValue - priorityQueue.peek());

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}