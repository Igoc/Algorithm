import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }

            return Integer.compare(o1[0], o2[0]);
        });

        int index = 0;
        int time = 0;
        int answer = 0;

        while (index < jobs.length || !priorityQueue.isEmpty()) {
            while (index < jobs.length && jobs[index][0] <= time) {
                priorityQueue.offer(jobs[index++]);
            }

            if (index < jobs.length && priorityQueue.isEmpty()) {
                priorityQueue.offer(jobs[index++]);
            }

            int[] job = priorityQueue.poll();

            if (time > job[0]) {
                answer += time - job[0];
                time += job[1];
            } else {
                time = job[0] + job[1];
            }

            answer += job[1];
        }

        return answer / jobs.length;
    }
}