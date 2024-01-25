import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            int K = Integer.parseInt(tokenizer.nextToken());

            int[] D = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<List<Integer>> nextBuildings = new ArrayList<>();
            int[] previousBuildingNumbers = new int[N];

            for (int building = 0; building < N; building++) {
                nextBuildings.add(new ArrayList<>());
            }

            for (int rule = 0; rule < K; rule++) {
                tokenizer = new StringTokenizer(in.readLine());

                int X = Integer.parseInt(tokenizer.nextToken()) - 1;
                int Y = Integer.parseInt(tokenizer.nextToken()) - 1;

                nextBuildings.get(X).add(Y);
                previousBuildingNumbers[Y]++;
            }

            int W = Integer.parseInt(in.readLine()) - 1;

            Queue<Integer> queue = new LinkedList<>();
            int[] requiredTime = new int[N];

            for (int building = 0; building < N; building++) {
                if (previousBuildingNumbers[building] == 0) {
                    queue.offer(building);
                    previousBuildingNumbers[building]--;
                }
            }

            while (!queue.isEmpty()) {
                int building = queue.poll();

                if (building == W) {
                    break;
                }

                for (int nextBuilding : nextBuildings.get(building)) {
                    requiredTime[nextBuilding] = Math.max(requiredTime[nextBuilding], D[building] + requiredTime[building]);
                    previousBuildingNumbers[nextBuilding]--;

                    if (previousBuildingNumbers[nextBuilding] == 0) {
                        queue.offer(nextBuilding);
                        previousBuildingNumbers[nextBuilding]--;
                    }
                }
            }

            out.write(D[W] + requiredTime[W] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}