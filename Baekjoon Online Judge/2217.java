import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int index = 0; index < N; index++) {
            priorityQueue.add(Integer.parseInt(in.readLine()));
        }

        int output = 0;

        for (int index = 0; index < N; index++) {
            int withstandWeight = priorityQueue.poll();

            if (withstandWeight * (priorityQueue.size() + 1) > output) {
                output = withstandWeight * (priorityQueue.size() + 1);
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}