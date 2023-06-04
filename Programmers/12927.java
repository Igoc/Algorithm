import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int work : works) {
            priorityQueue.offer(work);
        }

        for (int time = 0; time < n; time++) {
            if (priorityQueue.peek() == 0) {
                break;
            }

            priorityQueue.offer(priorityQueue.poll() - 1);
        }

        long answer = 0L;

        while (!priorityQueue.isEmpty() && priorityQueue.peek() != 0) {
            long work = priorityQueue.poll();

            answer += work * work;
        }

        return answer;
    }
}