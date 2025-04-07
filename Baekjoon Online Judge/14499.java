import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Dice {
    private int[] sides;
    private int[] values;

    public Dice() {
        this.sides = new int[]{0, 4, 5, 1, 3, 2};
        this.values = new int[6];
    }

    public void roll(int direction) {
        switch (direction) {
            case 0:
                sides = new int[]{sides[4], sides[1], sides[5], sides[3], sides[2], sides[0]};
                break;

            case 1:
                sides = new int[]{sides[5], sides[1], sides[4], sides[3], sides[0], sides[2]};
                break;

            case 2:
                sides = new int[]{sides[1], sides[2], sides[3], sides[0], sides[4], sides[5]};
                break;

            case 3:
                sides = new int[]{sides[3], sides[0], sides[1], sides[2], sides[4], sides[5]};
                break;
        }
    }

    public int getTop() {
        return values[sides[0]];
    }

    public int getBottom() {
        return values[sides[2]];
    }

    public void setBottom(int value) {
        values[sides[2]] = value;
    }
}

public class Main {
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        int x = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[N][M];

        for (int row = 0; row < N; row++) {
            map[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] queries = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(query -> query - 1)
                .toArray();

        Dice dice = new Dice();

        for (int query : queries) {
            int col = x + DX[query];
            int row = y + DY[query];

            if (col >= 0 && col < M && row >= 0 && row < N) {
                dice.roll(query);

                if (map[row][col] == 0) {
                    map[row][col] = dice.getBottom();
                } else {
                    dice.setBottom(map[row][col]);
                    map[row][col] = 0;
                }

                x = col;
                y = row;

                out.write(dice.getTop() + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}