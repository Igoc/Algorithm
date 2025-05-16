import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> stations = new ArrayList<>();
        List<List<Integer>> routes = new ArrayList<>();

        for (int station = 0; station < N; station++) {
            stations.add(new ArrayList<>());
        }

        for (int route = 0; route < L; route++) {
            tokenizer = new StringTokenizer(in.readLine());
            routes.add(new ArrayList<>());

            while (tokenizer.hasMoreTokens()) {
                int station = Integer.parseInt(tokenizer.nextToken());

                if (station == -1) {
                    break;
                }

                stations.get(station - 1).add(route);
                routes.get(route).add(station - 1);
            }
        }

        tokenizer = new StringTokenizer(in.readLine());

        int origin = Integer.parseInt(tokenizer.nextToken()) - 1;
        int destination = Integer.parseInt(tokenizer.nextToken()) - 1;

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[L];
        int[] transferNumbers = new int[N];

        Arrays.fill(transferNumbers, Integer.MAX_VALUE);
        transferNumbers[origin] = 0;

        for (int route : stations.get(origin)) {
            if (visited[route]) {
                continue;
            }

            visited[route] = true;

            for (int nextStation : routes.get(route)) {
                if (transferNumbers[nextStation] == Integer.MAX_VALUE) {
                    deque.offer(nextStation);
                    transferNumbers[nextStation] = 0;
                }
            }
        }

        while (!deque.isEmpty()) {
            int station = deque.poll();

            for (int route : stations.get(station)) {
                if (visited[route]) {
                    continue;
                }

                visited[route] = true;

                for (int nextStation : routes.get(route)) {
                    if (transferNumbers[nextStation] == Integer.MAX_VALUE) {
                        deque.offer(nextStation);
                        transferNumbers[nextStation] = transferNumbers[station] + 1;
                    }
                }
            }
        }

        if (transferNumbers[destination] == Integer.MAX_VALUE) {
            transferNumbers[destination] = -1;
        }

        out.write(String.valueOf(transferNumbers[destination]));

        in.close();
        out.flush();
        out.close();
    }
}