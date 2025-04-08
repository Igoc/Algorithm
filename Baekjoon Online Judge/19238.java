import java.io.*;
import java.util.*;

class Coord {
    public int row;
    public int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Space extends Coord {
    public int distance;

    public Space(int row, int col, int distance) {
        super(row, col);
        this.distance = distance;
    }
}

class Passenger extends Space implements Comparable<Passenger> {
    public int index;

    public Passenger(int row, int col, int distance, int index) {
        super(row, col, distance);
        this.index = index;
    }

    @Override
    public int compareTo(Passenger o) {
        if (distance == o.distance) {
            if (row == o.row) {
                return Integer.compare(col, o.col);
            }

            return Integer.compare(row, o.row);
        }

        return Integer.compare(distance, o.distance);
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
        int fuel = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[N][N];

        for (int row = 0; row < N; row++) {
            map[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Coord baekjoon = parseCoord(new StringTokenizer(in.readLine()));
        Coord[] origins = new Coord[M];
        Coord[] destinations = new Coord[M];

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            origins[index] = parseCoord(tokenizer);
            destinations[index] = parseCoord(tokenizer);
        }

        boolean[] moved = new boolean[M];

        while (M-- > 0) {
            Passenger passenger = selectPassenger(map, baekjoon, origins, moved);

            if (passenger.distance > fuel) {
                fuel = -1;
                break;
            }

            baekjoon = origins[passenger.index];
            fuel -= passenger.distance;

            Coord destination = destinations[passenger.index];
            int distance = measureDistances(map, baekjoon)[destination.row][destination.col];

            if (distance > fuel) {
                fuel = -1;
                break;
            }

            baekjoon = destination;
            fuel += distance;
            moved[passenger.index] = true;
        }

        out.write(String.valueOf(fuel));

        in.close();
        out.flush();
        out.close();
    }

    public static Coord parseCoord(StringTokenizer tokenizer) {
        int row = Integer.parseInt(tokenizer.nextToken()) - 1;
        int col = Integer.parseInt(tokenizer.nextToken()) - 1;

        return new Coord(row, col);
    }

    public static Passenger selectPassenger(int[][] map, Coord baekjoon, Coord[] origins, boolean[] moved) {
        PriorityQueue<Passenger> priorityQueue = new PriorityQueue<>();
        int[][] distances = measureDistances(map, baekjoon);

        for (int index = 0; index < origins.length; index++) {
            if (!moved[index]) {
                priorityQueue.offer(new Passenger(origins[index].row, origins[index].col, distances[origins[index].row][origins[index].col], index));
            }
        }

        return priorityQueue.peek();
    }

    public static int[][] measureDistances(int[][] map, Coord base) {
        Deque<Space> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        int[][] result = new int[map.length][map[0].length];

        for (int row = 0; row < map.length; row++) {
            Arrays.fill(result[row], Integer.MAX_VALUE);
        }

        deque.offer(new Space(base.row, base.col, 0));
        visited[base.row][base.col] = true;
        result[base.row][base.col] = 0;

        while (!deque.isEmpty()) {
            Space space = deque.poll();

            for (int dir = 0; dir < 4; dir++) {
                int row = space.row + DY[dir];
                int col = space.col + DX[dir];

                if (col >= 0 && col < map[0].length && row >= 0 && row < map.length && map[row][col] == 0 && !visited[row][col]) {
                    deque.offer(new Space(row, col, space.distance + 1));
                    visited[row][col] = true;
                    result[row][col] = space.distance + 1;
                }
            }
        }

        return result;
    }
}