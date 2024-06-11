import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

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
        int K = Integer.parseInt(tokenizer.nextToken());

        int[][] minerals = new int[N][M];

        for (int row = 0; row < N; row++) {
            minerals[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int minimumPerformance = 1;
        int maximumPerformance = 1000000;

        while (minimumPerformance <= maximumPerformance) {
            int performance = (minimumPerformance + maximumPerformance) / 2;

            boolean[][] visits = new boolean[N][M];
            int mineralNumber = 0;

            for (int row = 0; row < N; row++) {
                mineralNumber += mine(new Coord(0, row), visits, performance, minerals);
                mineralNumber += mine(new Coord(M - 1, row), visits, performance, minerals);
            }

            for (int col = 0; col < M; col++) {
                mineralNumber += mine(new Coord(col, 0), visits, performance, minerals);
            }

            if (mineralNumber < K) {
                minimumPerformance = performance + 1;
            } else {
                maximumPerformance = performance - 1;
            }
        }

        out.write(String.valueOf(minimumPerformance));

        in.close();
        out.flush();
        out.close();
    }

    public static int mine(Coord source, boolean[][] visits, int performance, int[][] minerals) {
        if (visits[source.y][source.x] || minerals[source.y][source.x] > performance) {
            return 0;
        }

        Stack<Coord> stack = new Stack<>();
        int output = 0;

        stack.push(source);
        visits[source.y][source.x] = true;
        output++;

        while (!stack.isEmpty()) {
            Coord coord = stack.pop();

            for (int dir = 0; dir < 4; dir++) {
                int x = coord.x + DX[dir];
                int y = coord.y + DY[dir];

                if (x >= 0 && x < minerals[0].length && y >= 0 && y < minerals.length) {
                    if (!visits[y][x] && minerals[y][x] <= performance) {
                        stack.push(new Coord(x, y));
                        visits[y][x] = true;
                        output++;
                    }
                }
            }
        }

        return output;
    }
}