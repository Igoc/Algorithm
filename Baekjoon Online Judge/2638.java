import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Pos {
    public int x;
    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void findOutside(int[][] graphPaper, boolean[][] outside) {
        Stack<Pos> stack = new Stack<>();
        boolean[][] visit = new boolean[graphPaper.length][graphPaper[0].length];

        stack.push(new Pos(0, 0));
        visit[0][0] = true;
        outside[0][0] = true;

        while (!stack.isEmpty()) {
            Pos pos = stack.pop();

            for (int dir = 0; dir < 4; dir++) {
                int x = pos.x + DX[dir];
                int y = pos.y + DY[dir];

                if (x >= 0 && x < graphPaper[0].length && y >= 0 && y < graphPaper.length) {
                    if (!visit[y][x] && graphPaper[y][x] == 0) {
                        stack.push(new Pos(x, y));
                        visit[y][x] = true;
                        outside[y][x] = true;
                    }
                }
            }
        }
    }

    public static int meltCheese(int[][] graphPaper, boolean[][] outside) {
        int meltedCheeseNumber = 0;

        for (int row = 0; row < graphPaper.length; row++) {
            for (int col = 0; col < graphPaper[row].length; col++) {
                if (graphPaper[row][col] == 1) {
                    int outsideNumber = 0;

                    for (int dir = 0; dir < 4; dir++) {
                        int x = col + DX[dir];
                        int y = row + DY[dir];

                        if (x >= 0 && x < graphPaper[0].length && y >= 0 && y < graphPaper.length) {
                            if (outside[y][x]) {
                                outsideNumber++;
                            }
                        }
                    }

                    if (outsideNumber >= 2) {
                        graphPaper[row][col] = 0;
                        meltedCheeseNumber++;
                    }
                }
            }
        }

        return meltedCheeseNumber;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] graphPaper = new int[N][M];
        int cheeseNumber = 0;

        for (int row = 0; row < N; row++) {
            graphPaper[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int col = 0; col < M; col++) {
                if (graphPaper[row][col] == 1) {
                    cheeseNumber++;
                }
            }
        }

        boolean[][] outside = new boolean[N][M];
        int output = 0;

        while (cheeseNumber > 0) {
            findOutside(graphPaper, outside);
            cheeseNumber -= meltCheese(graphPaper, outside);
            output++;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}