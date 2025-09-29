import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(in.readLine());

        int[][] jungles = new int[M + 1][N + 1];
        int[][] oceans = new int[M + 1][N + 1];
        int[][] ices = new int[M + 1][N + 1];

        for (int y = 1; y <= M; y++) {
            String row = in.readLine();

            for (int x = 1; x <= N; x++) {
                switch (row.charAt(x - 1)) {
                    case 'J':
                        jungles[y][x] = 1;
                        break;

                    case 'O':
                        oceans[y][x] = 1;
                        break;

                    case 'I':
                        ices[y][x] = 1;
                        break;
                }

                jungles[y][x] += jungles[y][x - 1];
                oceans[y][x] += oceans[y][x - 1];
                ices[y][x] += ices[y][x - 1];
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= M; y++) {
                jungles[y][x] += jungles[y - 1][x];
                oceans[y][x] += oceans[y - 1][x];
                ices[y][x] += ices[y - 1][x];
            }
        }

        for (int area = 0; area < K; area++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());

            int jungle = jungles[c][d] - jungles[c][b - 1] - jungles[a - 1][d] + jungles[a - 1][b - 1];
            int ocean = oceans[c][d] - oceans[c][b - 1] - oceans[a - 1][d] + oceans[a - 1][b - 1];
            int ice = ices[c][d] - ices[c][b - 1] - ices[a - 1][d] + ices[a - 1][b - 1];

            out.write(jungle + " " + ocean + " " + ice + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}