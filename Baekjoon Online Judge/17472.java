import java.io.*;
import java.util.*;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Bridge {
    public int island;
    public int distance;

    public Bridge(int island, int distance) {
        this.island = island;
        this.distance = distance;
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[N][M];

        for (int row = 0; row < N; row++) {
            map[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .map(area -> area - 2)
                    .toArray();
        }

        Deque<Coord> deque = new ArrayDeque<>();
        List<List<Coord>> islands = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] != -1) {
                    continue;
                }

                islands.add(new ArrayList<>());

                List<Coord> island = islands.get(islands.size() - 1);

                map[row][col] = islands.size() - 1;
                deque.offer(new Coord(col, row));
                island.add(new Coord(col, row));

                while (!deque.isEmpty()) {
                    Coord coord = deque.poll();

                    for (int dir = 0; dir < 4; dir++) {
                        int x = coord.x + DX[dir];
                        int y = coord.y + DY[dir];

                        if (x >= 0 && x < M && y >= 0 && y < N && map[y][x] == -1) {
                            map[y][x] = islands.size() - 1;
                            deque.offer(new Coord(x, y));
                            island.add(new Coord(x, y));
                        }
                    }
                }
            }
        }

        int[][] distances = new int[islands.size()][islands.size()];

        Arrays.stream(distances)
                .forEach(distance -> Arrays.fill(distance, Integer.MAX_VALUE));

        for (int island = 0; island < islands.size(); island++) {
            distances[island][island] = 0;
        }

        for (int island = 0; island < islands.size(); island++) {
            for (Coord coord : islands.get(island)) {
                for (int dir = 0; dir < 4; dir++) {
                    int x = coord.x + DX[dir];
                    int y = coord.y + DY[dir];
                    int distance = 0;

                    while (x >= 0 && x < M && y >= 0 && y < N && map[y][x] == -2) {
                        x += DX[dir];
                        y += DY[dir];
                        distance++;
                    }

                    if (x >= 0 && x < M && y >= 0 && y < N && distance > 1) {
                        distances[island][map[y][x]] = Math.min(distances[island][map[y][x]], distance);
                    }
                }
            }
        }

        PriorityQueue<Bridge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        boolean[] visits = new boolean[islands.size()];
        int output = 0;

        priorityQueue.offer(new Bridge(0, 0));

        while (!priorityQueue.isEmpty()) {
            Bridge bridge = priorityQueue.poll();

            if (visits[bridge.island]) {
                continue;
            }

            visits[bridge.island] = true;
            output += bridge.distance;

            for (int island = 0; island < islands.size(); island++) {
                if (distances[bridge.island][island] != Integer.MAX_VALUE && !visits[island]) {
                    priorityQueue.offer(new Bridge(island, distances[bridge.island][island]));
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