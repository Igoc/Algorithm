import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        int[] houses = new int[N];

        for (int index = 0; index < N; index++) {
            houses[index] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(houses);

        int minimumDistance = 1;
        int maximumDistance = houses[N - 1] - houses[0];

        while (minimumDistance <= maximumDistance) {
            int distance = (minimumDistance + maximumDistance) / 2;

            int routerHouse = houses[0];
            int routerNumber = 1;

            for (int index = 1; index < N; index++) {
                if (houses[index] - routerHouse >= distance) {
                    routerHouse = houses[index];
                    routerNumber++;
                }
            }

            if (routerNumber >= C) {
                minimumDistance = distance + 1;
            } else {
                maximumDistance = distance - 1;
            }
        }

        out.write(String.valueOf(maximumDistance));

        in.close();
        out.flush();
        out.close();
    }
}