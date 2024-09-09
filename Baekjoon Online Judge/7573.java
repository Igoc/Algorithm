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
        int l = Integer.parseInt(tokenizer.nextToken()) / 2;
        int M = Integer.parseInt(tokenizer.nextToken());

        Coord[] fishes = new Coord[M];

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int y = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());

            fishes[index] = new Coord(x, y);
        }

        int output = 0;

        for (Coord boat : fishes) {
            for (int width = 1; width < l; width++) {
                int height = l - width;

                for (int offset = -height; offset <= 0; offset++) {
                    int fishNumber = 0;

                    for (Coord fish : fishes) {
                        if (fish.x >= boat.x && fish.x <= boat.x + width && fish.y >= boat.y + offset && fish.y <= boat.y + offset + height) {
                            fishNumber++;
                        }
                    }

                    output = Math.max(output, fishNumber);
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}