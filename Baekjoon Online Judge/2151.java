import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Light extends Coord implements Comparable<Light> {
    public int direction;
    public int mirrorNumber;

    public Light(int x, int y, int direction, int mirrorNumber) {
        super(x, y);
        this.direction = direction;
        this.mirrorNumber = mirrorNumber;
    }

    @Override
    public int compareTo(Light o) {
        return Integer.compare(mirrorNumber, o.mirrorNumber);
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        char[][] house = new char[N][N];
        List<Coord> doors = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            house[row] = in.readLine().toCharArray();

            for (int col = 0; col < N; col++) {
                if (house[row][col] == '#') {
                    doors.add(new Coord(col, row));
                }
            }
        }

        PriorityQueue<Light> priorityQueue = new PriorityQueue<>();
        int[][][] mirrorNumbers = new int[N][N][4];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                for (int dir = 0; dir < 4; dir++) {
                    mirrorNumbers[row][col][dir] = Integer.MAX_VALUE;
                }
            }
        }

        for (int dir = 0; dir < 4; dir++) {
            priorityQueue.offer(new Light(doors.get(0).x, doors.get(0).y, dir, 0));
            mirrorNumbers[doors.get(0).y][doors.get(0).x][dir] = 0;
        }

        while (!priorityQueue.isEmpty()) {
            Light light = priorityQueue.poll();

            int x = light.x + DX[light.direction];
            int y = light.y + DY[light.direction];

            if (x >= 0 && x < N && y >= 0 && y < N && house[y][x] != '*') {
                if (mirrorNumbers[y][x][light.direction] < light.mirrorNumber) {
                    continue;
                }

                mirrorNumbers[y][x][light.direction] = light.mirrorNumber;

                if (house[y][x] == '#') {
                    break;
                }

                if (house[y][x] == '!') {
                    priorityQueue.offer(new Light(x, y, (light.direction + 1) % 4, light.mirrorNumber + 1));
                    priorityQueue.offer(new Light(x, y, (light.direction + 3) % 4, light.mirrorNumber + 1));
                }

                priorityQueue.offer(new Light(x, y, light.direction, light.mirrorNumber));
            }
        }

        int output = Integer.MAX_VALUE;

        for (int dir = 0; dir < 4; dir++) {
            output = Math.min(output, mirrorNumbers[doors.get(1).y][doors.get(1).x][dir]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}