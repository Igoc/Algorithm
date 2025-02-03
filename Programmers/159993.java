import java.util.ArrayDeque;
import java.util.Queue;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class State {
    public Coord coord;
    public int time;
    public int lever;

    public State(Coord coord, int time, int lever) {
        this.coord = coord;
        this.time = time;
        this.lever = lever;
    }
}

class Solution {
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {-1, 0, 1, 0};

    public int solution(String[] maps) {
        Coord start = new Coord(-1, -1);
        Coord end = new Coord(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (int y = 0; y < maps.length; y++) {
            for (int x = 0; x < maps[y].length(); x++) {
                char room = maps[y].charAt(x);

                if (room == 'S') {
                    start.x = x;
                    start.y = y;
                } else if (room == 'E') {
                    end.x = x;
                    end.y = y;
                }
            }
        }

        Queue<State> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[maps.length][maps[0].length()][2];
        int answer = -1;

        queue.offer(new State(start, 0, 0));
        visited[start.y][start.x][0] = true;

        while (!queue.isEmpty()) {
            State state = queue.poll();

            if (state.coord.x == end.x && state.coord.y == end.y && state.lever == 1) {
                answer = state.time;
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int x = state.coord.x + DX[dir];
                int y = state.coord.y + DY[dir];

                if (x >= 0 && x < maps[0].length() && y >= 0 && y < maps.length && maps[y].charAt(x) != 'X' && !visited[y][x][state.lever]) {
                    State nextState = new State(new Coord(x, y), state.time + 1, state.lever);

                    if (maps[y].charAt(x) == 'L') {
                        nextState.lever = 1;
                    }

                    queue.offer(nextState);
                    visited[y][x][nextState.lever] = true;
                }
            }
        }

        return answer;
    }
}