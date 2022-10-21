import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        for (int row = 0; row < N; row++) {
            for (int col = 0; col <= row; col++) {
                out.write('*');
            }

            out.write('\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}