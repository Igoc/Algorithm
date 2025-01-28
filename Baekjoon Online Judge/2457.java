import java.io.*;
import java.util.*;

class Flower {
    public int startDate;
    public int endDate;

    public Flower(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

public class Main {
    static final int[] ACCUMULATED_DATES = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Flower> flowers = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int startMonth = Integer.parseInt(tokenizer.nextToken());
            int startDay = Integer.parseInt(tokenizer.nextToken());
            int endMonth = Integer.parseInt(tokenizer.nextToken());
            int endDay = Integer.parseInt(tokenizer.nextToken());

            flowers.add(new Flower(ACCUMULATED_DATES[startMonth] + startDay, ACCUMULATED_DATES[endMonth] + endDay));
        }

        flowers.sort(Comparator.comparingInt(o -> o.startDate));

        PriorityQueue<Flower> priorityQueue = new PriorityQueue<>(Comparator.<Flower>comparingInt(o -> o.endDate).reversed());
        int index = 0;
        int date = ACCUMULATED_DATES[3] + 1;
        int output = 0;

        while (date <= ACCUMULATED_DATES[12]) {
            while (index < N && flowers.get(index).startDate <= date) {
                priorityQueue.offer(flowers.get(index++));
            }

            if (priorityQueue.isEmpty()) {
                break;
            }

            date = priorityQueue.poll().endDate;
            output++;
        }

        if (date <= ACCUMULATED_DATES[12]) {
            output = 0;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}