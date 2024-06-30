import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Coord {
    public int x;
    public int y;
    public int count;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
        this.count = 0;
    }
}

public class Main {
    static final int[] QUADRANT = {3, 2, 4, 1};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Coord[] fruits = new Coord[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int fx = Integer.parseInt(tokenizer.nextToken());
            int fy = Integer.parseInt(tokenizer.nextToken());

            fruits[index] = new Coord(fx, fy);
        }

        Map<String, Coord> skewers = new HashMap<>();

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int ex = Integer.parseInt(tokenizer.nextToken());
            int ey = Integer.parseInt(tokenizer.nextToken());

            int gcd = calculateGreatestCommonDivisor(ex, ey);
            int quadrant = findQuadrant(ex, ey);

            String hash = (ex / gcd) + "," + (ey / gcd) + "," + quadrant;

            if (skewers.containsKey(hash)) {
                Coord skewer = skewers.get(hash);

                if (Math.abs(ex) > Math.abs(skewer.x) || Math.abs(ey) > Math.abs(skewer.y)) {
                    skewer.x = ex;
                    skewer.y = ey;
                }
            } else {
                skewers.put(hash, new Coord(ex, ey));
            }
        }

        int output = 0;

        for (Coord fruit : fruits) {
            int gcd = calculateGreatestCommonDivisor(fruit.x, fruit.y);
            int quadrant = findQuadrant(fruit.x, fruit.y);

            String hash = (fruit.x / gcd) + "," + (fruit.y / gcd) + "," + quadrant;

            if (skewers.containsKey(hash)) {
                Coord skewer = skewers.get(hash);

                if (Math.abs(fruit.x) <= Math.abs(skewer.x) && Math.abs(fruit.y) <= Math.abs(skewer.y)) {
                    skewer.count++;
                    output = Math.max(output, skewer.count);
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateGreatestCommonDivisor(int a, int b) {
        while (b != 0) {
            int r = a % b;

            a = b;
            b = r;
        }

        return a;
    }

    public static int findQuadrant(int x, int y) {
        x = (x >= 0) ? (1) : (0);
        y = (y >= 0) ? (1) : (0);

        return QUADRANT[x * 2 + y];
    }
}