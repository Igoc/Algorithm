import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] room = new int[N][M];
        int trashNumber = 0;

        for (int y = 0; y < N; y++) {
            tokenizer = new StringTokenizer(in.readLine());

            for (int x = 0; x < M; x++) {
                room[y][x] = Integer.parseInt(tokenizer.nextToken());

                if (room[y][x] == 1) {
                    trashNumber++;
                }
            }
        }

        int output = 0;

        while (trashNumber > 0) {
            int start = 0;

            for (int y = 0; y < N; y++) {
                for (int x = start; x < M; x++) {
                    if (room[y][x] == 1) {
                        room[y][x] = 0;
                        trashNumber--;
                        start = x;
                    }
                }
            }

            output++;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}