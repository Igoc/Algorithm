import java.util.Arrays;
import java.util.PriorityQueue;

class Path implements Comparable<Path> {
    public int vertex;
    public int distance;

    public Path(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(distance, o.distance);
    }
}

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] distances = new int[n][n];

        Arrays.stream(distances)
                .forEach(distance -> Arrays.fill(distance, Integer.MAX_VALUE));

        for (int[] fare : fares) {
            int c = fare[0] - 1;
            int d = fare[1] - 1;
            int f = fare[2];

            distances[c][d] = f;
            distances[d][c] = f;
        }

        findShortestDistances(s - 1, distances);
        findShortestDistances(a - 1, distances);
        findShortestDistances(b - 1, distances);

        int answer = Integer.MAX_VALUE;

        for (int vertex = 0; vertex < n; vertex++) {
            if (distances[s - 1][vertex] != Integer.MAX_VALUE && distances[a - 1][vertex] != Integer.MAX_VALUE && distances[b - 1][vertex] != Integer.MAX_VALUE) {
                answer = Math.min(answer, distances[s - 1][vertex] + distances[a - 1][vertex] + distances[b - 1][vertex]);
            }
        }

        return answer;
    }

    public void findShortestDistances(int source, int[][] distances) {
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[distances.length];

        for (int vertex = 0; vertex < distances.length; vertex++) {
            if (distances[source][vertex] != Integer.MAX_VALUE) {
                priorityQueue.offer(new Path(vertex, distances[source][vertex]));
            }
        }

        distances[source][source] = 0;
        visit[source] = true;

        while (!priorityQueue.isEmpty()) {
            Path path = priorityQueue.poll();

            if (visit[path.vertex]) {
                continue;
            }

            visit[path.vertex] = true;

            for (int vertex = 0; vertex < distances.length; vertex++) {
                if (distances[path.vertex][vertex] != Integer.MAX_VALUE && distances[source][path.vertex] + distances[path.vertex][vertex] < distances[source][vertex]) {
                    distances[source][vertex] = distances[source][path.vertex] + distances[path.vertex][vertex];
                    priorityQueue.offer(new Path(vertex, distances[source][vertex]));
                }
            }
        }
    }
}