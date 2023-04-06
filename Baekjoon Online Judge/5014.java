import java.io.*;
import java.util.*;

class Elevator {
    public int floor;
    public int moveCount;

    public Elevator(int floor, int moveCount) {
        this.floor = floor;
        this.moveCount = moveCount;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int F = Integer.parseInt(tokenizer.nextToken());
        int S = Integer.parseInt(tokenizer.nextToken());
        int G = Integer.parseInt(tokenizer.nextToken());
        int U = Integer.parseInt(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        Queue<Elevator> queue = new LinkedList<>();
        boolean[] visit = new boolean[F + 1];
        int minMoveCount = 0;

        if (S != G) {
            queue.offer(new Elevator(S, 0));
            visit[S] = true;
            minMoveCount = -1;
        }

        while (!queue.isEmpty()) {
            Elevator elevator = queue.poll();
            int newMoveCount = elevator.moveCount + 1;

            int upstairs = elevator.floor + U;

            if (upstairs <= F && !visit[upstairs]) {
                if (upstairs == G) {
                    minMoveCount = newMoveCount;

                    break;
                }

                queue.offer(new Elevator(upstairs, newMoveCount));
                visit[upstairs] = true;
            }

            int downstairs = elevator.floor - D;

            if (downstairs >= 1 && !visit[downstairs]) {
                if (downstairs == G) {
                    minMoveCount = newMoveCount;

                    break;
                }

                queue.offer(new Elevator(downstairs, newMoveCount));
                visit[downstairs] = true;
            }
        }

        out.write((minMoveCount == -1) ? ("use the stairs") : (String.valueOf(minMoveCount)));

        in.close();
        out.flush();
        out.close();
    }
}