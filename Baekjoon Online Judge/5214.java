import java.io.*;
import java.util.*;

class Station {
    public int index;
    public int depth;

    public Station(int index, int depth) {
        this.index = index;
        this.depth = depth;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] hypertubes = new int[M][K];
        Map<Integer, List<Integer>> connectedHypertubes = new HashMap<>();

        for (int hypertube = 0; hypertube < M; hypertube++) {
            tokenizer = new StringTokenizer(in.readLine());

            for (int station = 0; station < K; station++) {
                int index = Integer.parseInt(tokenizer.nextToken()) - 1;

                hypertubes[hypertube][station] = index;
                connectedHypertubes.computeIfAbsent(index, key -> new ArrayList<>());
                connectedHypertubes.get(index).add(hypertube);
            }
        }

        Deque<Station> deque = new ArrayDeque<>();
        boolean[] visits = new boolean[N];
        int output = -1;

        deque.offer(new Station(0, 1));
        visits[0] = true;

        while (!deque.isEmpty()) {
            Station station = deque.poll();

            if (station.index == N - 1) {
                output = station.depth;
                break;
            }

            if (!connectedHypertubes.containsKey(station.index)) {
                continue;
            }

            for (int hypertube : connectedHypertubes.get(station.index)) {
                for (int index : hypertubes[hypertube]) {
                    if (!visits[index]) {
                        deque.offer(new Station(index, station.depth + 1));
                        visits[index] = true;
                    }
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}