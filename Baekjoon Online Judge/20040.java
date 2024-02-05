import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        int[] parents = new int[n];

        for (int index = 0; index < n; index++) {
            parents[index] = index;
        }

        int cycleTurn = 0;

        for (int turn = 1; turn <= m; turn++) {
            tokenizer = new StringTokenizer(in.readLine());

            if (cycleTurn != 0) {
                continue;
            }

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            if (findParent(x, parents) == findParent(y, parents)) {
                cycleTurn = turn;
            }

            mergeParent(x, y, parents);
        }

        out.write(String.valueOf(cycleTurn));

        in.close();
        out.flush();
        out.close();
    }

    public static int findParent(int index, int[] parents) {
        if (parents[index] == index) {
            return index;
        }

        return parents[index] = findParent(parents[index], parents);
    }

    public static void mergeParent(int x, int y, int[] parents) {
        x = findParent(x, parents);
        y = findParent(y, parents);

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }
}