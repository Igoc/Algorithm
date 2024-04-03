import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[][] S = new int[N][N];

        for (int row = 0; row < N; row++) {
            S[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        out.write(String.valueOf(calculateMinimumStatDifference(new int[N / 2], 0, new int[N / 2], 0, S, Integer.MAX_VALUE)));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateMinimumStatDifference(int[] startTeam, int startTeamNumber, int[] linkTeam, int linkTeamNumber, int[][] stats, int minimumStatDifference) {
        if (startTeamNumber + linkTeamNumber == stats.length) {
            int startTeamStat = 0;
            int linkTeamStat = 0;

            for (int row = 0; row < stats.length / 2; row++) {
                for (int col = 0; col < stats.length / 2; col++) {
                    startTeamStat += stats[startTeam[row]][startTeam[col]];
                    linkTeamStat += stats[linkTeam[row]][linkTeam[col]];
                }
            }

            return Math.min(minimumStatDifference, Math.abs(startTeamStat - linkTeamStat));
        }

        if (startTeamNumber < stats.length / 2) {
            startTeam[startTeamNumber] = startTeamNumber + linkTeamNumber;
            minimumStatDifference = calculateMinimumStatDifference(startTeam, startTeamNumber + 1, linkTeam, linkTeamNumber, stats, minimumStatDifference);
        }

        if (linkTeamNumber < stats.length / 2) {
            linkTeam[linkTeamNumber] = startTeamNumber + linkTeamNumber;
            minimumStatDifference = calculateMinimumStatDifference(startTeam, startTeamNumber, linkTeam, linkTeamNumber + 1, stats, minimumStatDifference);
        }

        return minimumStatDifference;
    }
}