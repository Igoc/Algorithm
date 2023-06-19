import java.util.LinkedList;
import java.util.Queue;

class Space {
    public int x;
    public int y;
    public int moveCount;

    public Space(int x, int y, int moveCount) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
    }
}

class Solution {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    public int solution(String[] board) {
        Space robot = new Space(0, 0, 0);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length(); col++) {
                if (board[row].charAt(col) == 'R') {
                    robot.x = col;
                    robot.y = row;

                    break;
                }
            }
        }

        Queue<Space> queue = new LinkedList<>();
        boolean[][] visit = new boolean[board.length][board[0].length()];

        queue.offer(robot);
        visit[robot.y][robot.x] = true;

        while (!queue.isEmpty()) {
            Space space = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int x = space.x + DX[dir];
                int y = space.y + DY[dir];

                while (x >= 0 && x < board[0].length() && y >= 0 && y < board.length && board[y].charAt(x) != 'D') {
                    x += DX[dir];
                    y += DY[dir];
                }

                x -= DX[dir];
                y -= DY[dir];

                if (board[y].charAt(x) == 'G') {
                    return space.moveCount + 1;
                }

                if (!visit[y][x]) {
                    queue.offer(new Space(x, y, space.moveCount + 1));
                    visit[y][x] = true;
                }
            }
        }

        return -1;
    }
}