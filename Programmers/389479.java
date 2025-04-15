import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] players, int m, int k) {
        Deque<Integer> servers = new ArrayDeque<>();
        int answer = 0;

        for (int time = 0; time < 24; time++) {
            while (!servers.isEmpty() && servers.peek() <= time) {
                servers.poll();
            }

            for (int server = servers.size(); server < players[time] / m; server++) {
                servers.offer(time + k);
                answer++;
            }
        }

        return answer;
    }
}