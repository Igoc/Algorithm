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
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());

        List<Integer> buildingHeights = findBuildingHeights(N, a, b);

        for (int buildingHeight : buildingHeights) {
            out.write(buildingHeight + " ");
        }

        in.close();
        out.flush();
        out.close();
    }

    public static List<Integer> findBuildingHeights(int buildingNumber, int gahuiBuildingNumber, int danbiBuildingNumber) {
        if (gahuiBuildingNumber + danbiBuildingNumber > buildingNumber + 1) {
            return List.of(-1);
        }

        List<Integer> buildingHeights = new ArrayList<>();

        if (gahuiBuildingNumber > 1) {
            for (int number = 1; number <= buildingNumber - gahuiBuildingNumber - danbiBuildingNumber + 1; number++) {
                buildingHeights.add(1);
            }
        }

        for (int height = 1; height < gahuiBuildingNumber; height++) {
            buildingHeights.add(height);
        }

        buildingHeights.add(Math.max(gahuiBuildingNumber, danbiBuildingNumber));

        if (gahuiBuildingNumber <= 1) {
            for (int number = 1; number <= buildingNumber - gahuiBuildingNumber - danbiBuildingNumber + 1; number++) {
                buildingHeights.add(1);
            }
        }

        for (int height = danbiBuildingNumber - 1; height >= 1; height--) {
            buildingHeights.add(height);
        }

        return buildingHeights;
    }
}