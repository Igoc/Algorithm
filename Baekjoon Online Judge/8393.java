import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int output = 0;

        for (int number = 1; number <= n; number++) {
            output += number;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}