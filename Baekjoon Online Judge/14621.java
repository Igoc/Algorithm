import java.io.*;
import java.util.*;

class Path {
    public int university;
    public int distance;

    public Path(int university, int distance) {
        this.university = university;
        this.distance = distance;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        char[] types = new char[N];
        List<List<Path>> paths = new ArrayList<>();

        tokenizer = new StringTokenizer(in.readLine());

        for (int university = 0; university < N; university++) {
            types[university] = tokenizer.nextToken().charAt(0);
            paths.add(new ArrayList<>());
        }

        for (int path = 0; path < M; path++) {
            tokenizer = new StringTokenizer(in.readLine());

            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;
            int d = Integer.parseInt(tokenizer.nextToken());

            if (types[u] != types[v]) {
                paths.get(u).add(new Path(v, d));
                paths.get(v).add(new Path(u, d));
            }
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        boolean[] visits = new boolean[N];
        int output = 0;

        priorityQueue.offer(new Path(0, 0));

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visits[path.university]) {
                continue;
            }

            visits[path.university] = true;
            output += path.distance;

            for (Path next : paths.get(path.university)) {
                if (!visits[next.university]) {
                    priorityQueue.offer(next);
                }
            }
        }

        for (boolean visit : visits) {
            if (!visit) {
                output = -1;

                break;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}