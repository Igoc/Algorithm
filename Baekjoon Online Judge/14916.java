import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int output = 0;

        while (n > 0) {
            if (n % 5 == 0) {
                output += n / 5;
                n = 0;
            } else {
                n -= 2;
                output++;
            }
        }

        if (n != 0) {
            output = -1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}