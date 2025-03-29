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

        List<List<List<Coord>>> switches = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            switches.add(new ArrayList<>());

            for (int x = 0; x < N; x++) {
                switches.get(y).add(new ArrayList<>());
            }
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;

            switches.get(y).get(x).add(new Coord(a, b));
        }

        Deque<Coord> deque = new ArrayDeque<>();
        boolean[][] lighted = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        int output = 0;

        deque.offer(new Coord(0, 0));
        lighted[0][0] = true;
        visited[0][0] = true;
        output++;

        while (!deque.isEmpty()) {
            Coord room = deque.poll();

            for (Coord nextRoom : switches.get(room.y).get(room.x)) {
                if (lighted[nextRoom.y][nextRoom.x]) {
                    continue;
                }

                lighted[nextRoom.y][nextRoom.x] = true;
                output++;

                for (int dir = 0; dir < 4; dir++) {
                    int x = nextRoom.x + DX[dir];
                    int y = nextRoom.y + DY[dir];

                    if (x >= 0 && x < N && y >= 0 && y < N && lighted[y][x] && visited[y][x]) {
                        deque.offer(new Coord(nextRoom.x, nextRoom.y));
                        visited[nextRoom.y][nextRoom.x] = true;
                        break;
                    }
                }
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = room.x + DX[dir];
                int y = room.y + DY[dir];

                if (x >= 0 && x < N && y >= 0 && y < N && lighted[y][x] && !visited[y][x]) {
                    deque.offer(new Coord(x, y));
                    visited[y][x] = true;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}