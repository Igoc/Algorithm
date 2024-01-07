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

    public Pos(Pos pos) {
        this.x = pos.x;
        this.y = pos.y;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        String[] map = new String[N];

        for (int row = 0; row < N; row++) {
            map[row] = in.readLine();
        }

        int[][] groups = new int[N][M];
        int safeZoneNumber = 0;

        Arrays.stream(groups)
                .forEach(group -> Arrays.fill(group, -1));

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (groups[row][col] != -1) {
                    continue;
                }

                Stack<Pos> stack = new Stack<>();

                stack.push(new Pos(col, row));
                groups[row][col] = row * M + col;

                while (!stack.isEmpty()) {
                    Pos currentPosition = stack.pop();
                    Pos nextPosition = new Pos(currentPosition);

                    switch (map[currentPosition.y].charAt(currentPosition.x)) {
                        case 'U':
                            nextPosition.y--;

                            break;

                        case 'D':
                            nextPosition.y++;

                            break;

                        case 'L':
                            nextPosition.x--;

                            break;

                        case 'R':
                            nextPosition.x++;

                            break;
                    }

                    if (groups[nextPosition.y][nextPosition.x] != -1) {
                        if (groups[nextPosition.y][nextPosition.x] == groups[currentPosition.y][currentPosition.x]) {
                            safeZoneNumber++;
                        }

                        break;
                    }

                    stack.push(nextPosition);
                    groups[nextPosition.y][nextPosition.x] = row * M + col;
                }
            }
        }

        out.write(String.valueOf(safeZoneNumber));

        in.close();
        out.flush();
        out.close();
    }
}