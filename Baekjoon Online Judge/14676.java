import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> nextBuildings = new ArrayList<>();
        int[] buildingNumbers = new int[N];
        int[] previousBuildingNumbers = new int[N];

        for (int building = 0; building < N; building++) {
            nextBuildings.add(new ArrayList<>());
        }

        for (int relation = 0; relation < M; relation++) {
            tokenizer = new StringTokenizer(in.readLine());

            int X = Integer.parseInt(tokenizer.nextToken()) - 1;
            int Y = Integer.parseInt(tokenizer.nextToken()) - 1;

            nextBuildings.get(X).add(Y);
            previousBuildingNumbers[Y]++;
        }

        boolean lier = false;

        loop:
        for (int log = 0; log < K; log++) {
            tokenizer = new StringTokenizer(in.readLine());

            int status = Integer.parseInt(tokenizer.nextToken());
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;

            switch (status) {
                case 1:
                    if (previousBuildingNumbers[a] != 0) {
                        lier = true;

                        break loop;
                    }

                    buildingNumbers[a]++;

                    if (buildingNumbers[a] == 1) {
                        for (int nextBuilding : nextBuildings.get(a)) {
                            previousBuildingNumbers[nextBuilding]--;
                        }
                    }

                    break;

                case 2:
                    if (buildingNumbers[a] == 0) {
                        lier = true;

                        break loop;
                    }

                    buildingNumbers[a]--;

                    if (buildingNumbers[a] == 0) {
                        for (int nextBuilding : nextBuildings.get(a)) {
                            previousBuildingNumbers[nextBuilding]++;
                        }
                    }

                    break;
            }
        }

        out.write((lier) ? ("Lier!") : ("King-God-Emperor"));

        in.close();
        out.flush();
        out.close();
    }
}