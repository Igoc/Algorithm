import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture {
    public int date;
    public int pay;

    public Lecture(int date, int pay) {
        this.date = date;
        this.pay = pay;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        Lecture[] lectures = new Lecture[n];
        int maximumDate = 0;

        for (int index = 0; index < n; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int p = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());

            lectures[index] = new Lecture(d, p);
            maximumDate = Math.max(maximumDate, d);
        }

        Arrays.sort(lectures, Comparator.<Lecture>comparingInt(o -> o.date).reversed());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int lectureIndex = 0;
        int output = 0;

        for (int date = maximumDate; date > 0; date--) {
            while (lectureIndex < n && lectures[lectureIndex].date >= date) {
                priorityQueue.offer(lectures[lectureIndex++].pay);
            }

            if (!priorityQueue.isEmpty()) {
                output += priorityQueue.poll();
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}