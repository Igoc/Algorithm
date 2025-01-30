import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[][] priorities = new int[n + 1][n + 1];

        for (int index = 0; index < k; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int earlierEvent = Integer.parseInt(tokenizer.nextToken());
            int laterEvent = Integer.parseInt(tokenizer.nextToken());

            priorities[earlierEvent][laterEvent] = -1;
            priorities[laterEvent][earlierEvent] = 1;
        }

        for (int waypoint = 1; waypoint <= n; waypoint++) {
            for (int origin = 1; origin <= n; origin++) {
                for (int destination = 1; destination <= n; destination++) {
                    if (priorities[origin][waypoint] != 0 && priorities[waypoint][destination] != 0 && priorities[origin][waypoint] == priorities[waypoint][destination]) {
                        priorities[origin][destination] = priorities[origin][waypoint];
                        priorities[destination][origin] = priorities[waypoint][destination] * -1;
                    }
                }
            }
        }

        int s = Integer.parseInt(in.readLine());

        for (int index = 0; index < s; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int event1 = Integer.parseInt(tokenizer.nextToken());
            int event2 = Integer.parseInt(tokenizer.nextToken());

            out.write(priorities[event1][event2] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}