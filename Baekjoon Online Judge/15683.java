import java.io.*;
import java.util.*;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] office = new int[N][M];
        List<Coord> cctvs = new ArrayList<>();
        int areaNumber = N * M;

        for (int y = 0; y < N; y++) {
            tokenizer = new StringTokenizer(in.readLine());

            for (int x = 0; x < M; x++) {
                office[y][x] = Integer.parseInt(tokenizer.nextToken());

                if (1 <= office[y][x] && office[y][x] <= 5) {
                    cctvs.add(new Coord(x, y));
                } else if (office[y][x] == 6) {
                    areaNumber--;
                }
            }
        }

        List<List<Set<Integer>>> coveredAreas = new ArrayList<>();

        for (Coord cctv : cctvs) {
            coveredAreas.add(findCoveredAreas(office, cctv));
        }

        out.write(String.valueOf(areaNumber - calculateMaximumMonitoredAreaNumber(coveredAreas, 0, new HashSet<>(), 0)));

        in.close();
        out.flush();
        out.close();
    }

    public static List<Set<Integer>> findCoveredAreas(int[][] office, Coord cctv) {
        List<List<Integer>> observableAreas = findObservableAreas(office, cctv);
        List<Set<Integer>> result = new ArrayList<>();

        switch (office[cctv.y][cctv.x]) {
            case 1:
                for (int rotation = 0; rotation < 4; rotation++) {
                    result.add(new HashSet<>(observableAreas.get(rotation)));
                }

                break;

            case 2:
                for (int rotation = 0; rotation < 2; rotation++) {
                    result.add(new HashSet<>(observableAreas.get(rotation)));
                    result.get(rotation).addAll(observableAreas.get(rotation + 2));
                }

                break;

            case 3:
                for (int rotation = 0; rotation < 4; rotation++) {
                    result.add(new HashSet<>(observableAreas.get(rotation)));
                    result.get(rotation).addAll(observableAreas.get((rotation + 1) % 4));
                }

                break;

            case 4:
                for (int rotation = 0; rotation < 4; rotation++) {
                    result.add(new HashSet<>(observableAreas.get(rotation)));
                    result.get(rotation).addAll(observableAreas.get((rotation + 1) % 4));
                    result.get(rotation).addAll(observableAreas.get((rotation + 2) % 4));
                }

                break;

            case 5:
                result.add(new HashSet<>(observableAreas.get(0)));
                result.get(0).addAll(observableAreas.get(1));
                result.get(0).addAll(observableAreas.get(2));
                result.get(0).addAll(observableAreas.get(3));

                break;
        }

        return result;
    }

    public static List<List<Integer>> findObservableAreas(int[][] office, Coord cctv) {
        List<List<Integer>> result = new ArrayList<>();

        for (int dir = 0; dir < 4; dir++) {
            Coord coord = new Coord(cctv.x, cctv.y);
            List<Integer> observableArea = new ArrayList<>();

            while (coord.x >= 0 && coord.x < office[0].length && coord.y >= 0 && coord.y < office.length) {
                if (office[coord.y][coord.x] == 6) {
                    break;
                }

                observableArea.add(coord.y * office[0].length + coord.x);
                coord.x += DX[dir];
                coord.y += DY[dir];
            }

            result.add(observableArea);
        }

        return result;
    }

    public static int calculateMaximumMonitoredAreaNumber(List<List<Set<Integer>>> coveredAreas, int index, Set<Integer> monitoredArea, int maximumMonitoredAreaNumber) {
        if (index >= coveredAreas.size()) {
            return Math.max(maximumMonitoredAreaNumber, monitoredArea.size());
        }

        for (Set<Integer> coveredArea : coveredAreas.get(index)) {
            Set<Integer> nextMonitoredArea = new HashSet<>();

            nextMonitoredArea.addAll(monitoredArea);
            nextMonitoredArea.addAll(coveredArea);

            maximumMonitoredAreaNumber = calculateMaximumMonitoredAreaNumber(coveredAreas, index + 1, nextMonitoredArea, maximumMonitoredAreaNumber);
        }

        return maximumMonitoredAreaNumber;
    }
}