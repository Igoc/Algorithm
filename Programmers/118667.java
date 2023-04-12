import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();

        long sumA = 0L;
        long sumB = 0L;

        for (int index = 0; index < queue1.length; index++) {
            queueA.offer(queue1[index]);
            queueB.offer(queue2[index]);

            sumA += queue1[index];
            sumB += queue2[index];
        }

        int answer = 0;
        int maxIteration = queue1.length * 4;

        while (answer < maxIteration && sumA != sumB) {
            if (sumA > sumB) {
                int element = queueA.poll();

                queueB.offer(element);

                sumA -= element;
                sumB += element;
            } else {
                int element = queueB.poll();

                queueA.offer(element);

                sumA += element;
                sumB -= element;
            }

            answer++;
        }

        if (sumA != sumB) {
            return -1;
        }

        return answer;
    }
}