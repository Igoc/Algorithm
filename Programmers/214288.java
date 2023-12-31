import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        return calculateMinimalWaitTime(new int[k], n, 0, reqs, Integer.MAX_VALUE);
    }

    public int calculateMinimalWaitTime(int[] mentorNumbers, int remainingMentorNumber, int type, int[][] requests, int minimalWaitTime) {
        if (remainingMentorNumber == 0) {
            return minimalWaitTime;
        }

        if (type == mentorNumbers.length - 1) {
            mentorNumbers[type] = remainingMentorNumber;

            List<PriorityQueue<Integer>> mentorQueues = new ArrayList<>();
            int waitTime = 0;

            for (int index = 0; index < mentorNumbers.length; index++) {
                mentorQueues.add(new PriorityQueue<>());
            }

            for (int[] request : requests) {
                PriorityQueue<Integer> mentor = mentorQueues.get(request[2] - 1);

                if (mentor.size() < mentorNumbers[request[2] - 1]) {
                    mentor.offer(request[0] + request[1]);

                    continue;
                }

                int endTime = mentor.poll();

                if (endTime > request[0]) {
                    waitTime += endTime - request[0];
                }

                mentor.offer(Math.max(request[0], endTime) + request[1]);
            }

            return Math.min(waitTime, minimalWaitTime);
        }

        for (int mentorNumber = 1; mentorNumber <= remainingMentorNumber; mentorNumber++) {
            mentorNumbers[type] = mentorNumber;
            minimalWaitTime = calculateMinimalWaitTime(mentorNumbers, remainingMentorNumber - mentorNumber, type + 1, requests, minimalWaitTime);
        }

        return minimalWaitTime;
    }
}