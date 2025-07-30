import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        List<List<Integer>> routes = new ArrayList<>();

        for (int index = 0; index <= n; index++) {
            routes.add(new ArrayList<>());
        }

        for (int[] route : lighthouse) {
            routes.get(route[0]).add(route[1]);
            routes.get(route[1]).add(route[0]);
        }

        int[][] litLighthouseCounts = new int[n + 1][2];

        traverseLighthouses(routes, 1, new boolean[n + 1], litLighthouseCounts);

        return Math.min(litLighthouseCounts[1][0], litLighthouseCounts[1][1]);
    }

    public void traverseLighthouses(List<List<Integer>> routes, int index, boolean[] isVisited, int[][] litLighthouseCounts) {
        isVisited[index] = true;
        litLighthouseCounts[index][1] = 1;

        for (int nextIndex : routes.get(index)) {
            if (isVisited[nextIndex]) {
                continue;
            }

            traverseLighthouses(routes, nextIndex, isVisited, litLighthouseCounts);

            litLighthouseCounts[index][0] += litLighthouseCounts[nextIndex][1];
            litLighthouseCounts[index][1] += Math.min(litLighthouseCounts[nextIndex][0], litLighthouseCounts[nextIndex][1]);
        }
    }
}