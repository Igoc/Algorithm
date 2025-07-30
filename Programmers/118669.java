import java.util.*;
import java.util.stream.Collectors;

class Route {
    public int node;
    public int intensity;

    public Route(int node, int intensity) {
        this.node = node;
        this.intensity = intensity;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Route>> routes = new ArrayList<>();

        for (int node = 0; node <= n; node++) {
            routes.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];

            routes.get(i).add(new Route(j, w));
            routes.get(j).add(new Route(i, w));
        }

        Set<Integer> summitSet = Arrays.stream(summits)
                .boxed()
                .collect(Collectors.toSet());

        PriorityQueue<Route> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.intensity));
        int[] intensities = new int[n + 1];

        Arrays.fill(intensities, Integer.MAX_VALUE);

        for (int gate : gates) {
            priorityQueue.offer(new Route(gate, 0));
            intensities[gate] = 0;
        }

        while (!priorityQueue.isEmpty()) {
            Route route = priorityQueue.poll();

            if (route.intensity > intensities[route.node]) {
                continue;
            }

            if (summitSet.contains(route.node)) {
                continue;
            }

            for (Route nextRoute : routes.get(route.node)) {
                int intensity = Math.max(route.intensity, nextRoute.intensity);

                if (intensities[nextRoute.node] > intensity) {
                    priorityQueue.offer(new Route(nextRoute.node, intensity));
                    intensities[nextRoute.node] = intensity;
                }
            }
        }

        int[] answer = {0, Integer.MAX_VALUE};

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensities[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = intensities[summit];
            }
        }

        return answer;
    }
}