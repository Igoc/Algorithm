import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int calculateMinimumWorkingHours(int[] cranes, int[] boxes) {
        if (cranes[cranes.length - 1] < boxes[boxes.length - 1]) {
            return -1;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int craneIndex = cranes.length - 1;

        for (int boxIndex = boxes.length - 1; boxIndex >= 0; boxIndex--) {
            while (craneIndex >= 0 && cranes[craneIndex] >= boxes[boxIndex]) {
                priorityQueue.offer(0);
                craneIndex--;
            }

            priorityQueue.offer(priorityQueue.poll() + 1);
        }

        while (priorityQueue.size() > 1) {
            priorityQueue.poll();
        }

        return priorityQueue.poll();
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] cranes = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int M = Integer.parseInt(in.readLine());

        int[] boxes = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        out.write(String.valueOf(calculateMinimumWorkingHours(cranes, boxes)));

        in.close();
        out.flush();
        out.close();
    }
}