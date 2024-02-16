import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());

            List<List<Integer>> lines = new ArrayList<>();

            for (int circle = 0; circle < n; circle++) {
                lines.add(new ArrayList<>());
            }

            for (int line = 0; line < m; line++) {
                tokenizer = new StringTokenizer(in.readLine());

                int x = Integer.parseInt(tokenizer.nextToken()) - 1;
                int y = Integer.parseInt(tokenizer.nextToken()) - 1;

                lines.get(x).add(y);
                lines.get(y).add(x);
            }

            Stack<Integer> stack = new Stack<>();
            boolean[] visits = new boolean[n];
            int[] colors = new int[n];
            boolean possible = true;

            Arrays.fill(colors, -1);

            loop:
            for (int circle = 0; circle < n; circle++) {
                if (visits[circle]) {
                    continue;
                }

                stack.push(circle);
                visits[circle] = true;
                colors[circle] = 0;

                while (!stack.isEmpty()) {
                    int currentCircle = stack.pop();

                    for (int nextCircle : lines.get(currentCircle)) {
                        if (colors[nextCircle] != -1 && colors[nextCircle] == colors[currentCircle]) {
                            possible = false;

                            break loop;
                        }

                        if (visits[nextCircle]) {
                            continue;
                        }

                        stack.push(nextCircle);
                        visits[nextCircle] = true;
                        colors[nextCircle] = (colors[currentCircle] + 1) % 2;
                    }
                }
            }

            out.write((possible) ? ("possible\n") : ("impossible\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}