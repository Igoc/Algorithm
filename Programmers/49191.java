import java.util.HashSet;
import java.util.Set;

class Player {
    public Set<Integer> winners;
    public Set<Integer> losers;

    public Player() {
        this.winners = new HashSet<>();
        this.losers = new HashSet<>();
    }
}

class Solution {
    public Set<Integer> findWinners(int player, Player[] players, boolean[] visit) {
        if (visit[player]) {
            return players[player].winners;
        }

        Set<Integer> winners = new HashSet<>(players[player].winners);

        visit[player] = true;

        for (int winner : winners) {
            players[player].winners.addAll(findWinners(winner, players, visit));
        }

        return players[player].winners;
    }

    public Set<Integer> findLosers(int player, Player[] players, boolean[] visit) {
        if (visit[player]) {
            return players[player].losers;
        }

        Set<Integer> losers = new HashSet<>(players[player].losers);

        visit[player] = true;

        for (int loser : losers) {
            players[player].losers.addAll(findLosers(loser, players, visit));
        }

        return players[player].losers;
    }

    public int solution(int n, int[][] results) {
        Player[] players = new Player[n];

        for (int player = 0; player < n; player++) {
            players[player] = new Player();
        }

        for (int[] result : results) {
            int A = result[0] - 1;
            int B = result[1] - 1;

            players[A].losers.add(B);
            players[B].winners.add(A);
        }

        boolean[][] visit = new boolean[2][n];
        int answer = 0;

        for (int player = 0; player < n; player++) {
            Set<Integer> winners = findWinners(player, players, visit[0]);
            Set<Integer> losers = findLosers(player, players, visit[1]);

            if (winners.size() + losers.size() == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}