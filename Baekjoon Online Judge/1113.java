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
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] heights = new int[N][M];

        for (int y = 0; y < N; y++) {
            String row = in.readLine();

            for (int x = 0; x < M; x++) {
                heights[y][x] = row.charAt(x) - '0';
            }
        }

        int output = 0;

        for (int height = 1; height <= 9; height++) {
            boolean[][] visited = new boolean[N][M];

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (heights[y][x] != height || visited[y][x]) {
                        continue;
                    }

                    Deque<Coord> deque = new ArrayDeque<>();
                    List<Coord> connectedCoords = new ArrayList<>();
                    boolean isLeaky = false;

                    deque.offer(new Coord(x, y));
                    connectedCoords.add(new Coord(x, y));
                    visited[y][x] = true;

                    while (!deque.isEmpty()) {
                        Coord coord = deque.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            Coord nextCoord = new Coord(coord.x + DX[dir], coord.y + DY[dir]);

                            if (nextCoord.x < 0 || nextCoord.x >= M || nextCoord.y < 0 || nextCoord.y >= N) {
                                isLeaky = true;
                                continue;
                            }

                            if (heights[nextCoord.y][nextCoord.x] <= height && !visited[nextCoord.y][nextCoord.x]) {
                                deque.offer(nextCoord);
                                visited[nextCoord.y][nextCoord.x] = true;

                                if (heights[nextCoord.y][nextCoord.x] == height) {
                                    connectedCoords.add(nextCoord);
                                }
                            }
                        }
                    }

                    if (!isLeaky) {
                        for (Coord connectedCoord : connectedCoords) {
                            heights[connectedCoord.y][connectedCoord.x]++;
                        }

                        output += connectedCoords.size();
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