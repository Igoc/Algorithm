import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int round = 0; round < enemy.length; round++) {
            priorityQueue.add(enemy[round]);

            if (round >= k) {
                if (n < priorityQueue.peek()) {
                    return round;
                }

                n -= priorityQueue.poll();
            }
        }

        return enemy.length;
    }
}