import java.util.*;

class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {-1, 0, 1, 0};

    public int solution(int[][] land) {
        int[][] lumps = new int[land.length][land[0].length];
        List<Integer> volumes = new ArrayList<>();
        boolean[][] visits = new boolean[land.length][land[0].length];

        Arrays.stream(lumps)
                .forEach(lump -> Arrays.fill(lump, -1));

        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[row].length; col++) {
                if (!visits[row][col] && land[row][col] == 1) {
                    Stack<Coordinate> stack = new Stack<>();
                    int volume = 0;

                    stack.push(new Coordinate(col, row));
                    visits[row][col] = true;
                    lumps[row][col] = volumes.size();
                    volume++;

                    while (!stack.isEmpty()) {
                        Coordinate coordinate = stack.pop();

                        for (int dir = 0; dir < 4; dir++) {
                            int x = coordinate.x + DX[dir];
                            int y = coordinate.y + DY[dir];

                            if (x >= 0 && x < land[row].length && y >= 0 && y < land.length && !visits[y][x] && land[y][x] == 1) {
                                stack.push(new Coordinate(x, y));
                                visits[y][x] = true;
                                lumps[y][x] = volumes.size();
                                volume++;
                            }
                        }
                    }

                    volumes.add(volume);
                }
            }
        }

        int answer = 0;

        for (int col = 0; col < land[0].length; col++) {
            Set<Integer> currentLumps = new HashSet<>();
            int volume = 0;

            for (int row = 0; row < land.length; row++) {
                if (lumps[row][col] != -1 && !currentLumps.contains(lumps[row][col])) {
                    currentLumps.add(lumps[row][col]);
                    volume += volumes.get(lumps[row][col]);
                }
            }

            answer = Math.max(answer, volume);
        }

        return answer;
    }
}