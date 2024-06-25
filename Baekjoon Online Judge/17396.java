import java.io.*;
import java.util.*;

class Path {
    public int point;
    public long time;

    public Path(int point, long time) {
        this.point = point;
        this.time = time;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] sights = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<List<Path>> paths = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            paths.add(new ArrayList<>());
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());

            paths.get(a).add(new Path(b, t));
            paths.get(b).add(new Path(a, t));
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o.time));
        long[] times = new long[N];

        priorityQueue.offer(new Path(0, 0L));
        Arrays.fill(times, Long.MAX_VALUE);

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (times[path.point] < path.time) {
                continue;
            }

            for (Path nextPath : paths.get(path.point)) {
                if (sights[nextPath.point] == 1 && nextPath.point != N - 1) {
                    continue;
                }

                if (path.time + nextPath.time < times[nextPath.point]) {
                    priorityQueue.offer(new Path(nextPath.point, path.time + nextPath.time));
                    times[nextPath.point] = path.time + nextPath.time;
                }
            }
        }

        if (times[N - 1] == Long.MAX_VALUE) {
            times[N - 1] = -1L;
        }

        out.write(String.valueOf(times[N - 1]));

        in.close();
        out.flush();
        out.close();
    }
}