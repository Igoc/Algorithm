import java.io.*;
import java.util.StringTokenizer;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Coord[] shootingStars = new Coord[K];

        for (int index = 0; index < K; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            shootingStars[index] = new Coord(x, y);
        }

        int output = Integer.MAX_VALUE;

        for (Coord baseShootingStar : shootingStars) {
            for (int offset = 0; offset <= L; offset++) {
                Coord trampolineTopLeft = new Coord(baseShootingStar.x, baseShootingStar.y - offset);
                Coord trampolineBottomRight = new Coord(trampolineTopLeft.x + L, trampolineTopLeft.y + L);
                int count = 0;

                for (Coord shootingStar : shootingStars) {
                    if (shootingStar.x >= trampolineTopLeft.x && shootingStar.x <= trampolineBottomRight.x && shootingStar.y >= trampolineTopLeft.y && shootingStar.y <= trampolineBottomRight.y) {
                        count++;
                    }
                }

                output = Math.min(output, K - count);
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}