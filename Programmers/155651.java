import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(String[][] book_time) {
        int[][] bookTime = new int[book_time.length][book_time[0].length];

        for (int index = 0; index < bookTime.length; index++) {
            String[] startTime = book_time[index][0].split(":");
            String[] endTime = book_time[index][1].split(":");

            bookTime[index][0] = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            bookTime[index][1] = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
        }

        Arrays.sort(bookTime, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int answer = 1;

        priorityQueue.add(bookTime[0][1]);

        for (int index = 1; index < bookTime.length; index++) {
            if (bookTime[index][0] < priorityQueue.peek() + 10) {
                answer++;
            } else {
                priorityQueue.poll();
            }

            priorityQueue.add(bookTime[index][1]);
        }

        return answer;
    }
}