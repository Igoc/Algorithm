import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int index = 0; index < N; index++) {
            int number = Integer.parseInt(in.readLine());

            if (maxHeap.isEmpty() || number <= maxHeap.peek()) {
                maxHeap.offer(number);

                if (maxHeap.size() > (index + 2) / 2) {
                    minHeap.offer(maxHeap.poll());
                }
            } else {
                minHeap.offer(number);

                if (minHeap.size() > (index + 1) / 2) {
                    maxHeap.offer(minHeap.poll());
                }
            }

            out.write(maxHeap.peek() + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}