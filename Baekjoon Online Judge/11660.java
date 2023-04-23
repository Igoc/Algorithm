import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[][] createSummedAreaTable(int[][] table) {
        int rowNumber = table.length;
        int colNumber = table[0].length;

        int[][] summedAreaTable = new int[rowNumber + 1][colNumber + 1];

        for (int row = 1; row <= rowNumber; row++) {
            for (int col = 1; col <= colNumber; col++) {
                summedAreaTable[row][col] += table[row - 1][col - 1] + summedAreaTable[row - 1][col] + summedAreaTable[row][col - 1] - summedAreaTable[row - 1][col - 1];
            }
        }

        return summedAreaTable;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] table = new int[N][N];

        for (int row = 0; row < N; row++) {
            table[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] summedAreaTable = createSummedAreaTable(table);

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x1 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y1 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            int output = summedAreaTable[x2][y2] - summedAreaTable[x1][y2] - summedAreaTable[x2][y1] + summedAreaTable[x1][y1];

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}