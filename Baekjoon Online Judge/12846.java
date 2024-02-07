import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Period {
    public int startDate;
    public int endDate;
}

class Work implements Comparable<Work> {
    public int date;
    public int salary;

    public Work(int date, int salary) {
        this.date = date;
        this.salary = salary;
    }

    @Override
    public int compareTo(Work o) {
        return Integer.compare(salary, o.salary);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[] T = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Period[] periods = new Period[n];

        for (int date = 0; date < n; date++) {
            periods[date] = new Period();
        }

        PriorityQueue<Work> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int date = 0; date < n; date++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek().salary > T[date]) {
                Work work = priorityQueue.poll();

                periods[work.date].endDate = date - 1;
            }

            priorityQueue.offer(new Work(date, T[date]));
        }

        while (!priorityQueue.isEmpty()) {
            Work work = priorityQueue.poll();

            periods[work.date].endDate = n - 1;
        }

        for (int date = n - 1; date >= 0; date--) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek().salary > T[date]) {
                Work work = priorityQueue.poll();

                periods[work.date].startDate = date + 1;
            }

            priorityQueue.offer(new Work(date, T[date]));
        }

        long output = 0L;

        for (int date = 0; date < n; date++) {
            output = Math.max(output, (long) (periods[date].endDate - periods[date].startDate + 1) * (long) T[date]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}