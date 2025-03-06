import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        int[][] connections = new int[N][N];

        for (int city = 0; city < N; city++) {
            connections[city] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            connections[city][city] = 1;
        }

        int[] plan = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(city -> city - 1)
                .toArray();

        for (int waypoint = 0; waypoint < N; waypoint++) {
            for (int origin = 0; origin < N; origin++) {
                for (int destination = 0; destination < N; destination++) {
                    if (connections[origin][waypoint] == 1 && connections[waypoint][destination] == 1) {
                        connections[origin][destination] = 1;
                    }
                }
            }
        }

        String output = "YES";

        for (int index = 1; index < M; index++) {
            if (connections[plan[index - 1]][plan[index]] == 0) {
                output = "NO";
                break;
            }
        }

        out.write(output);

        in.close();
        out.flush();
        out.close();
    }
}