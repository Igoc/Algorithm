import java.util.Arrays;
import java.util.PriorityQueue;

class Path implements Comparable<Path> {
    public int island;
    public int cost;

    public Path(int island, int cost) {
        this.island = island;
        this.cost = cost;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(cost, o.cost);
    }
}

class Solution {
    public int solution(int n, int[][] costs) {
        int[][] bridges = new int[n][n];

        Arrays.stream(bridges)
                .forEach(bridge -> Arrays.fill(bridge, Integer.MAX_VALUE));

        for (int[] cost : costs) {
            bridges[cost[0]][cost[1]] = Math.min(bridges[cost[0]][cost[1]], cost[2]);
            bridges[cost[1]][cost[0]] = Math.min(bridges[cost[1]][cost[0]], cost[2]);
        }

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[n];

        priorityQueue.offer(new Path(0, 0));

        int answer = 0;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visit[path.island]) {
                continue;
            }

            visit[path.island] = true;
            answer += path.cost;

            for (int island = 0; island < n; island++) {
                if (!visit[island] && bridges[path.island][island] != Integer.MAX_VALUE) {
                    priorityQueue.offer(new Path(island, bridges[path.island][island]));
                }
            }
        }

        return answer;
    }
}