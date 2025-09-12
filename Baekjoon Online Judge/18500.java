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

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        char[][] cave = new char[R][C];

        for (int row = R - 1; row >= 0; row--) {
            cave[row] = in.readLine().toCharArray();
        }

        int N = Integer.parseInt(in.readLine());

        int[] thrownHeights = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(thrownHeight -> thrownHeight - 1)
                .toArray();

        for (int turn = 0; turn < N; turn++) {
            if (turn % 2 == 0) {
                for (int col = 0; col < C; col++) {
                    if (cave[thrownHeights[turn]][col] == 'x') {
                        cave[thrownHeights[turn]][col] = '.';
                        break;
                    }
                }
            } else {
                for (int col = C - 1; col >= 0; col--) {
                    if (cave[thrownHeights[turn]][col] == 'x') {
                        cave[thrownHeights[turn]][col] = '.';
                        break;
                    }
                }
            }

            List<Coord> floatingCluster = findFloatingCluster(cave);

            adjustFloatingCluster(cave, floatingCluster);
        }

        for (int row = R - 1; row >= 0; row--) {
            out.write(String.valueOf(cave[row]) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }

    public static List<Coord> findFloatingCluster(char[][] cave) {
        Deque<Coord> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[cave.length][cave[0].length];

        for (int row = 0; row < cave.length; row++) {
            for (int col = 0; col < cave[0].length; col++) {
                if (cave[row][col] == '.' || visited[row][col]) {
                    continue;
                }

                List<Coord> cluster = new ArrayList<>();
                boolean isFloating = true;

                cluster.add(new Coord(col, row));
                deque.offer(cluster.get(0));
                visited[row][col] = true;

                while (!deque.isEmpty()) {
                    Coord coord = deque.poll();

                    if (coord.y == 0) {
                        isFloating = false;
                    }

                    for (int dir = 0; dir < 4; dir++) {
                        int x = coord.x + DX[dir];
                        int y = coord.y + DY[dir];

                        if (x < 0 || x >= cave[0].length || y < 0 || y >= cave.length || cave[y][x] == '.' || visited[y][x]) {
                            continue;
                        }

                        cluster.add(new Coord(x, y));
                        deque.offer(cluster.get(cluster.size() - 1));
                        visited[y][x] = true;
                    }
                }

                if (isFloating) {
                    return cluster;
                }
            }
        }

        return new ArrayList<>();
    }

    public static void adjustFloatingCluster(char[][] cave, List<Coord> floatingCluster) {
        boolean[][] isFloated = new boolean[cave.length][cave[0].length];

        for (Coord coord : floatingCluster) {
            isFloated[coord.y][coord.x] = true;
        }

        int minRowOffset = cave.length - 1;

        for (Coord coord : floatingCluster) {
            for (int row = coord.y - 1; row >= 0; row--) {
                if (cave[row][coord.x] == 'x' && !isFloated[row][coord.x]) {
                    minRowOffset = Math.min(coord.y - row - 1, minRowOffset);
                    break;
                }
            }

            minRowOffset = Math.min(coord.y, minRowOffset);
        }

        floatingCluster.sort(Comparator.comparingInt(o -> o.y));

        for (Coord coord : floatingCluster) {
            cave[coord.y][coord.x] = '.';
            cave[coord.y - minRowOffset][coord.x] = 'x';
        }
    }
}