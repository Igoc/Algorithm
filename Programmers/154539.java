import java.util.Comparator;
import java.util.PriorityQueue;

class Number {
    public int index;
    public int value;

    public Number(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        PriorityQueue<Number> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        int[] answer = new int[numbers.length];

        for (int index = 0; index < numbers.length; index++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek().value < numbers[index]) {
                answer[priorityQueue.poll().index] = numbers[index];
            }

            priorityQueue.offer(new Number(index, numbers[index]));
        }

        while (!priorityQueue.isEmpty()) {
            answer[priorityQueue.poll().index] = -1;
        }

        return answer;
    }
}