import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Robot {
    public int x;
    public int y;
    public int direction;
    public int commandNumber;

    public Robot(int x, int y, int direction, int commandNumber) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commandNumber = commandNumber;
    }
}

public class Main {
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[M][N];

        for (int row = 0; row < M; row++) {
            map[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        tokenizer = new StringTokenizer(in.readLine());

        int startY = Integer.parseInt(tokenizer.nextToken()) - 1;
        int startX = Integer.parseInt(tokenizer.nextToken()) - 1;
        int startDirection = Integer.parseInt(tokenizer.nextToken()) - 1;

        tokenizer = new StringTokenizer(in.readLine());

        int endY = Integer.parseInt(tokenizer.nextToken()) - 1;
        int endX = Integer.parseInt(tokenizer.nextToken()) - 1;
        int endDirection = Integer.parseInt(tokenizer.nextToken()) - 1;

        Queue<Robot> queue = new LinkedList<>();
        boolean[][][] visits = new boolean[M][N][4];
        int output = 0;

        queue.offer(new Robot(startX, startY, startDirection, 0));
        visits[startY][startX][startDirection] = true;

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();

            if (robot.x == endX && robot.y == endY && robot.direction == endDirection) {
                output = robot.commandNumber;

                break;
            }

            int left = (robot.direction / 2 * 2 + 2) % 4;
            int right = (robot.direction / 2 * 2 + 3) % 4;

            if (!visits[robot.y][robot.x][left]) {
                queue.offer(new Robot(robot.x, robot.y, left, robot.commandNumber + 1));
                visits[robot.y][robot.x][left] = true;
            }

            if (!visits[robot.y][robot.x][right]) {
                queue.offer(new Robot(robot.x, robot.y, right, robot.commandNumber + 1));
                visits[robot.y][robot.x][right] = true;
            }

            for (int distance = 1; distance <= 3; distance++) {
                int x = robot.x + DX[robot.direction] * distance;
                int y = robot.y + DY[robot.direction] * distance;

                if (x < 0 || x >= N || y < 0 || y >= M || map[y][x] == 1) {
                    break;
                }

                if (!visits[y][x][robot.direction]) {
                    queue.offer(new Robot(x, y, robot.direction, robot.commandNumber + 1));
                    visits[y][x][robot.direction] = true;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}