import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        List<Integer> connections = new ArrayList<>();

        for (int leftUtilityPole = 0; leftUtilityPole < N; leftUtilityPole++) {
            int rightUtilityPole = Integer.parseInt(tokenizer.nextToken());

            if (connections.isEmpty() || connections.get(connections.size() - 1) < rightUtilityPole) {
                connections.add(rightUtilityPole);
            } else {
                int index = Collections.binarySearch(connections, rightUtilityPole);

                if (index < 0) {
                    connections.set(-index - 1, rightUtilityPole);
                }
            }
        }

        out.write(String.valueOf(N - connections.size()));

        in.close();
        out.flush();
        out.close();
    }
}