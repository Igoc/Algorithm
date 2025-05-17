import java.util.*;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public static final int[] DX = {0, 1, 0, -1};
    public static final int[] DY = {-1, 0, 1, 0};

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();

        Map<Character, List<Coord>> containers = new HashMap<>();
        boolean[][] released = new boolean[n][m];
        boolean[][] outsides = new boolean[n][m];

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                char type = storage[y].charAt(x);

                containers.putIfAbsent(type, new ArrayList<>());
                containers.get(type).add(new Coord(x, y));

                if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                    outsides[y][x] = true;
                }
            }
        }

        int answer = n * m;

        for (String request : requests) {
            char type = request.charAt(0);
            boolean forklift = (request.length() == 1);

            if (!containers.containsKey(type)) {
                continue;
            }

            if (forklift) {
                answer -= releaseByForklift(containers.get(type), released, outsides);
            } else {
                answer -= releaseByCrane(containers.get(type), released, outsides);
            }
        }

        return answer;
    }

    public int releaseByForklift(List<Coord> coords, boolean[][] released, boolean[][] outsides) {
        Deque<Coord> releasedOutsideCoords = new ArrayDeque<>();
        int result = 0;

        for (Coord coord : coords) {
            if (released[coord.y][coord.x] || !outsides[coord.y][coord.x]) {
                continue;
            }

            released[coord.y][coord.x] = true;
            releasedOutsideCoords.offer(coord);
            result++;
        }

        updateOutsides(releasedOutsideCoords, released, outsides);

        return result;
    }

    public int releaseByCrane(List<Coord> coords, boolean[][] released, boolean[][] outsides) {
        Deque<Coord> releasedOutsideCoords = new ArrayDeque<>();
        int result = 0;

        for (Coord coord : coords) {
            if (released[coord.y][coord.x]) {
                continue;
            }

            if (outsides[coord.y][coord.x]) {
                releasedOutsideCoords.offer(coord);
            }

            released[coord.y][coord.x] = true;
            result++;
        }

        updateOutsides(releasedOutsideCoords, released, outsides);

        return result;
    }

    public void updateOutsides(Deque<Coord> releasedOutsideCoords, boolean[][] released, boolean[][] outsides) {
        while (!releasedOutsideCoords.isEmpty()) {
            Coord coord = releasedOutsideCoords.poll();

            for (int dir = 0; dir < 4; dir++) {
                int x = coord.x + DX[dir];
                int y = coord.y + DY[dir];

                if (x >= 0 && x < outsides[0].length && y >= 0 && y < outsides.length && !outsides[y][x]) {
                    outsides[y][x] = true;

                    if (released[y][x]) {
                        releasedOutsideCoords.offer(new Coord(x, y));
                    }
                }
            }
        }
    }
}