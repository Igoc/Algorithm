import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        for (int index = 1; index <= 9; index++) {
            out.write(String.format("%d * %d = %d\n", N, index, N * index));
        }

        in.close();
        out.flush();
        out.close();
    }
}