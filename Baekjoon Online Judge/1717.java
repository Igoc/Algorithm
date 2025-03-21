import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        int[] parents = new int[n + 1];

        for (int index = 0; index <= n; index++) {
            parents[index] = index;
        }

        for (int operation = 0; operation < m; operation++) {
            tokenizer = new StringTokenizer(in.readLine());

            int type = Integer.parseInt(tokenizer.nextToken());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            if (type == 0) {
                union(parents, a, b);
            } else if (type == 1) {
                out.write((find(parents, a) == find(parents, b)) ? ("YES\n") : ("NO\n"));
            }
        }

        in.close();
        out.flush();
        out.close();
    }

    public static void union(int[] parents, int x, int y) {
        x = find(parents, x);
        y = find(parents, y);

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }

    public static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents, parents[x]);
    }
}