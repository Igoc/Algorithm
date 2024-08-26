import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            String N = in.readLine();

            if (N == null) {
                break;
            }

            StringTokenizer tokenizer = new StringTokenizer(in.readLine().trim());
            List<Integer> risingTrend = new ArrayList<>();

            for (int date = 0; date < Integer.parseInt(N.trim()); date++) {
                int p = Integer.parseInt(tokenizer.nextToken());

                if (risingTrend.isEmpty() || risingTrend.get(risingTrend.size() - 1) < p) {
                    risingTrend.add(p);
                } else {
                    int index = Collections.binarySearch(risingTrend, p);

                    if (index < 0) {
                        risingTrend.set(-index - 1, p);
                    }
                }
            }

            out.write(risingTrend.size() + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}