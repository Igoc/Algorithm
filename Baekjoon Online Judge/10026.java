import java.io.*;
import java.util.*;

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

    public static int searchPaintingArea(String[] painting, boolean[][] searchState, int row, int col, int N) {
        if (searchState[row][col]) {
            return 0;
        }

        Queue<Pos> queue = new LinkedList<>();
        char color = painting[row].charAt(col);

        queue.offer(new Pos(col, row));
        searchState[row][col] = true;

        while (!queue.isEmpty()) {
            Pos pos = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int x = pos.x + DX[dir];
                int y = pos.y + DY[dir];

                if (x >= 0 && x < N && y >= 0 && y < N) {
                    if (!searchState[y][x] && painting[y].charAt(x) == color) {
                        queue.offer(new Pos(x, y));
                        searchState[y][x] = true;
                    }
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        String[] normalPainting = new String[N];
        String[] redGreenBlindPainting = new String[N];

        for (int index = 0; index < N; index++) {
            String row = in.readLine();

            normalPainting[index] = row;
            redGreenBlindPainting[index] = row.replace('G', 'R');
        }

        boolean[][] normalPaintingSearchState = new boolean[N][N];
        boolean[][] redGreenBlindPaintingSearchState = new boolean[N][N];

        int normalPaintingAreaNumber = 0;
        int redGreenBlindPaintingAreaNumber = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                normalPaintingAreaNumber += searchPaintingArea(normalPainting, normalPaintingSearchState, row, col, N);
                redGreenBlindPaintingAreaNumber += searchPaintingArea(redGreenBlindPainting, redGreenBlindPaintingSearchState, row, col, N);
            }
        }

        out.write(normalPaintingAreaNumber + " " + redGreenBlindPaintingAreaNumber);

        in.close();
        out.flush();
        out.close();
    }
}