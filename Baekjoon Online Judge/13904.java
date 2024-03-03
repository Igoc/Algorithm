import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Homework {
    public int deadline;
    public int score;

    public Homework(int deadline, int score) {
        this.deadline = deadline;
        this.score = score;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getScore() {
        return score;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Homework[] homework = new Homework[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int d = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());

            homework[index] = new Homework(d, w);
        }

        Arrays.sort(homework, Comparator.comparingInt(Homework::getDeadline).reversed());

        PriorityQueue<Homework> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Homework::getScore).reversed());
        int index = 0;
        int output = 0;

        for (int date = homework[0].deadline; date >= 1; date--) {
            while (index < N && homework[index].deadline >= date) {
                priorityQueue.offer(homework[index++]);
            }

            if (!priorityQueue.isEmpty()) {
                output += priorityQueue.poll().score;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}