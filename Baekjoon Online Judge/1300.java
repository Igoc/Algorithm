import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int k = Integer.parseInt(in.readLine());

        long minimumNumber = 1;
        long maximumNumber = (long) N * (long) N;

        while (minimumNumber <= maximumNumber) {
            long number = (minimumNumber + maximumNumber) / 2;
            long index = 1;

            int col = 1;

            while (col < N && col < number) {
                col++;
            }

            index += col;

            for (int row = 2; row <= N; row++) {
                while (row * col > number) {
                    col--;
                }

                index += col;
            }

            if (index <= k) {
                minimumNumber = number + 1;
            } else {
                maximumNumber = number - 1;
            }
        }

        out.write(String.valueOf(minimumNumber));

        in.close();
        out.flush();
        out.close();
    }
}