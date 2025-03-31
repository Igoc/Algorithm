import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Cable {
    public int computer;
    public int length;

    public Cable(int computer, int length) {
        this.computer = computer;
        this.length = length;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] cables = new int[N][N];
        int totalLength = 0;

        Arrays.stream(cables)
                .forEach(lengths -> Arrays.fill(lengths, Integer.MAX_VALUE));

        for (int origin = 0; origin < N; origin++) {
            String lengths = in.readLine();

            for (int destination = 0; destination < N; destination++) {
                int length = lengths.charAt(destination);

                if (length == '0') {
                    continue;
                }

                if ('a' <= length && length <= 'z') {
                    length = length - 'a' + 1;
                } else {
                    length = length - 'A' + 27;
                }

                cables[origin][destination] = Math.min(cables[origin][destination], length);
                cables[destination][origin] = Math.min(cables[destination][origin], length);
                totalLength += length;
            }
        }

        PriorityQueue<Cable> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.length));
        boolean[] visited = new boolean[N];
        int minimumLength = 0;

        priorityQueue.offer(new Cable(0, 0));

        while (!priorityQueue.isEmpty()) {
            Cable cable = priorityQueue.poll();

            if (visited[cable.computer]) {
                continue;
            }

            visited[cable.computer] = true;
            minimumLength += cable.length;

            for (int computer = 0; computer < N; computer++) {
                if (cables[cable.computer][computer] != Integer.MAX_VALUE && !visited[computer]) {
                    priorityQueue.offer(new Cable(computer, cables[cable.computer][computer]));
                }
            }
        }

        for (int computer = 0; computer < N; computer++) {
            if (!visited[computer]) {
                minimumLength = totalLength + 1;
                break;
            }
        }

        out.write(String.valueOf(totalLength - minimumLength));

        in.close();
        out.flush();
        out.close();
    }
}