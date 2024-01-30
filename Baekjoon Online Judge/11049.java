import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Matrix {
    public int row;
    public int col;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Matrix[] matrices = new Matrix[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            matrices[index] = new Matrix(r, c);
        }

        int[][] table = new int[N][N];

        for (int row = 1; row < N; row++) {
            Arrays.fill(table[row], Integer.MAX_VALUE);

            for (int col = 0; col < N - row; col++) {
                int tableRow = 0;
                int tableCol = col + 1;

                while (tableRow < row) {
                    table[row][col] = Math.min(
                            table[row][col],
                            table[tableRow][col] + table[row - tableRow - 1][tableCol] + matrices[col].row * matrices[tableRow + col].col * matrices[row - tableRow - 1 + tableCol].col
                    );

                    tableRow++;
                    tableCol++;
                }
            }
        }

        out.write(String.valueOf(table[N - 1][0]));

        in.close();
        out.flush();
        out.close();
    }
}