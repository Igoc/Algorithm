import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Star {
    public double x;
    public double y;

    public Star(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Line implements Comparable<Line> {
    public int index;
    public double cost;

    public Line(int index, double cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Line o) {
        return Double.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        Star[] stars = new Star[n];

        for (int index = 0; index < n; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            double x = Double.parseDouble(tokenizer.nextToken());
            double y = Double.parseDouble(tokenizer.nextToken());

            stars[index] = new Star(x, y);
        }

        double[][] distances = new double[n][n];

        for (int left = 0; left < n; left++) {
            for (int right = left + 1; right < n; right++) {
                double distance = Math.sqrt(Math.pow(stars[left].x - stars[right].x, 2) + Math.pow(stars[left].y - stars[right].y, 2));

                distances[left][right] = distance;
                distances[right][left] = distance;
            }
        }

        PriorityQueue<Line> priorityQueue = new PriorityQueue<>();
        boolean[] visits = new boolean[n];
        double output = 0.0;

        for (int index = 1; index < n; index++) {
            priorityQueue.offer(new Line(index, distances[0][index]));
        }

        visits[0] = true;

        while (!priorityQueue.isEmpty()) {
            Line line = priorityQueue.poll();

            if (visits[line.index]) {
                continue;
            }

            visits[line.index] = true;
            output += line.cost;

            for (int index = 0; index < n; index++) {
                if (!visits[index]) {
                    priorityQueue.offer(new Line(index, distances[line.index][index]));
                }
            }
        }

        out.write(String.format("%.2f", output));

        in.close();
        out.flush();
        out.close();
    }
}