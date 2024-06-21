import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] villagers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<List<Integer>> paths = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            paths.add(new ArrayList<>());
        }

        for (int index = 0; index < N - 1; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;

            paths.get(A).add(B);
            paths.get(B).add(A);
        }

        int[][] maximumVillagers = new int[N][2];

        calculateMaximumVillagers(0, new boolean[N], maximumVillagers, villagers, paths);

        out.write(String.valueOf(Math.max(maximumVillagers[0][0], maximumVillagers[0][1])));

        in.close();
        out.flush();
        out.close();
    }

    public static void calculateMaximumVillagers(int town, boolean[] visits, int[][] maximumVillagers, int[] villagers, List<List<Integer>> paths) {
        visits[town] = true;
        maximumVillagers[town][1] = villagers[town];

        for (int adjacencyTown : paths.get(town)) {
            if (!visits[adjacencyTown]) {
                calculateMaximumVillagers(adjacencyTown, visits, maximumVillagers, villagers, paths);
                maximumVillagers[town][0] += Math.max(maximumVillagers[adjacencyTown][0], maximumVillagers[adjacencyTown][1]);
                maximumVillagers[town][1] += maximumVillagers[adjacencyTown][0];
            }
        }
    }
}