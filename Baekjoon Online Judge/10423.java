import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Cable implements Comparable<Cable> {
    public int left;
    public int right;
    public int cost;

    public Cable(int left, int right, int cost) {
        this.left = left;
        this.right = right;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cable o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        boolean[] powerPlants = new boolean[N];

        tokenizer = new StringTokenizer(in.readLine());

        for (int index = 0; index < K; index++) {
            powerPlants[Integer.parseInt(tokenizer.nextToken()) - 1] = true;
        }

        Cable[] cables = new Cable[M];

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;
            int w = Integer.parseInt(tokenizer.nextToken());

            cables[index] = new Cable(u, v, w);
        }

        Arrays.sort(cables);

        int[] parents = new int[N];

        for (int index = 0; index < N; index++) {
            parents[index] = index;
        }

        int output = 0;

        for (Cable cable : cables) {
            int left = findParent(cable.left, parents);
            int right = findParent(cable.right, parents);

            if (left == right || powerPlants[left] && powerPlants[right]) {
                continue;
            }

            connectCity(left, right, parents, powerPlants);
            output += cable.cost;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static int findParent(int city, int[] parents) {
        if (parents[city] == city) {
            return city;
        }

        return parents[city] = findParent(parents[city], parents);
    }

    public static void connectCity(int left, int right, int[] parents, boolean[] powerPlants) {
        left = findParent(left, parents);
        right = findParent(right, parents);

        if (powerPlants[left] || powerPlants[right]) {
            if (powerPlants[right]) {
                parents[left] = right;
            } else {
                parents[right] = left;
            }
        } else if (left > right) {
            parents[left] = right;
        } else {
            parents[right] = left;
        }
    }
}