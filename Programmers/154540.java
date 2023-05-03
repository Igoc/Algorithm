import java.util.*;

class Pos {
    public int x;
    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    public int[] solution(String[] maps) {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        List<Integer> answer = new ArrayList<>();

        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[0].length(); col++) {
                if (!visit[row][col] && maps[row].charAt(col) != 'X') {
                    int provisionCount = 0;

                    queue.offer(new Pos(col, row));
                    visit[row][col] = true;
                    provisionCount += maps[row].charAt(col) - '0';

                    while (!queue.isEmpty()) {
                        Pos pos = queue.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int x = pos.x + DX[dir];
                            int y = pos.y + DY[dir];

                            if (x >= 0 && x < maps[0].length() && y >= 0 && y < maps.length) {
                                if (!visit[y][x] && maps[y].charAt(x) != 'X') {
                                    queue.offer(new Pos(x, y));
                                    visit[y][x] = true;
                                    provisionCount += maps[y].charAt(x) - '0';
                                }
                            }
                        }
                    }

                    answer.add(provisionCount);
                }
            }
        }

        if (answer.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(answer);

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}