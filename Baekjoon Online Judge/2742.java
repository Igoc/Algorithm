import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        for (int number = N; number >= 1; number--) {
            out.write(String.valueOf(number) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}