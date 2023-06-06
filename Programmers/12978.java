import java.util.Arrays;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int[][] distances = new int[N][N];

        Arrays.stream(distances)
                .forEach(distance -> Arrays.fill(distance, Integer.MAX_VALUE));

        for (int[] connection : road) {
            int a = connection[0] - 1;
            int b = connection[1] - 1;
            int c = connection[2];

            distances[a][b] = Math.min(distances[a][b], c);
            distances[b][a] = Math.min(distances[b][a], c);
        }

        boolean[] visit = new boolean[N];

        visit[0] = true;

        for (int sequence = 1; sequence < N; sequence++) {
            int visitIndex = 0;

            for (int index = 1; index < N; index++) {
                if (!visit[index] && distances[0][index] < distances[0][visitIndex]) {
                    visitIndex = index;
                }
            }

            visit[visitIndex] = true;

            for (int index = 1; index < N; index++) {
                if (!visit[index] && distances[visitIndex][index] != Integer.MAX_VALUE) {
                    distances[0][index] = Math.min(distances[0][index], distances[0][visitIndex] + distances[visitIndex][index]);
                }
            }
        }

        int answer = 1;

        for (int index = 1; index < N; index++) {
            if (distances[0][index] <= K) {
                answer++;
            }
        }

        return answer;
    }
}