import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
    public int box;
    public int row;
    public int col;

    public Tomato(int box, int row, int col) {
        this.box = box;
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static final int[] DD = {-1, 1, 0, 0, 0, 0};
    static final int[] DX = {0, 0, 0, 1, 0, -1};
    static final int[] DY = {0, 0, -1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());
        int H = Integer.parseInt(tokenizer.nextToken());

        int[][][] boxes = new int[H][N][M];

        Queue<Tomato> queue = new ArrayDeque<>();
        int unripeTomatoNumber = 0;

        for (int box = 0; box < H; box++) {
            for (int row = 0; row < N; row++) {
                tokenizer = new StringTokenizer(in.readLine());

                for (int col = 0; col < M; col++) {
                    boxes[box][row][col] = Integer.parseInt(tokenizer.nextToken());

                    if (boxes[box][row][col] == 0) {
                        unripeTomatoNumber++;
                    } else if (boxes[box][row][col] == 1) {
                        queue.offer(new Tomato(box, row, col));
                    }
                }
            }
        }

        int output = 0;

        while (true) {
            Queue<Tomato> nextQueue = new ArrayDeque<>();

            while (!queue.isEmpty()) {
                Tomato tomato = queue.poll();

                for (int dir = 0; dir < 6; dir++) {
                    int box = tomato.box + DD[dir];
                    int row = tomato.row + DY[dir];
                    int col = tomato.col + DX[dir];

                    if (box >= 0 && box < H && row >= 0 && row < N && col >= 0 && col < M && boxes[box][row][col] == 0) {
                        boxes[box][row][col] = 1;
                        nextQueue.offer(new Tomato(box, row, col));
                        unripeTomatoNumber--;
                    }
                }
            }

            if (nextQueue.isEmpty()) {
                break;
            }

            queue = nextQueue;
            output++;
        }

        if (unripeTomatoNumber != 0) {
            output = -1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}