import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<int[]> findHanoiTowerSolution(int source, int destination, int diskNumber) {
        if (diskNumber <= 0) {
            return new ArrayList<>();
        }

        int waypoint = 3 - (source + destination) % 3;

        List<int[]> solution = findHanoiTowerSolution(source, waypoint, diskNumber - 1);
        solution.add(new int[]{source, destination});
        solution.addAll(findHanoiTowerSolution(waypoint, destination, diskNumber - 1));

        return solution;
    }

    public int[][] solution(int n) {
        return findHanoiTowerSolution(1, 3, n).stream()
                .toArray(int[][]::new);
    }
}