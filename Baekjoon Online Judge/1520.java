import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Cell implements Comparable<Cell> {
    public int x;
    public int y;
    public int height;

    public Cell(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    @Override
    public int compareTo(Cell o) {
        return Integer.compare(height, o.height);
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[M][N];
        Cell[] cells = new Cell[M * N];

        for (int row = 0; row < M; row++) {
            map[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int col = 0; col < N; col++) {
                cells[row * N + col] = new Cell(col, row, map[row][col]);
            }
        }

        Arrays.sort(cells, Comparator.reverseOrder());

        int[][] pathNumbers = new int[M][N];

        pathNumbers[0][0] = 1;

        for (Cell cell : cells) {
            for (int dir = 0; dir < 4; dir++) {
                int x = cell.x + DX[dir];
                int y = cell.y + DY[dir];

                if (x >= 0 && x < N && y >= 0 && y < M && map[y][x] < cell.height) {
                    pathNumbers[y][x] += pathNumbers[cell.y][cell.x];
                }
            }
        }

        out.write(String.valueOf(pathNumbers[M - 1][N - 1]));

        in.close();
        out.flush();
        out.close();
    }
}