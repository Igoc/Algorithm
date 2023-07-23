import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        boolean[] remainders = new boolean[42];

        for (int index = 0; index < 10; index++) {
            remainders[Integer.parseInt(in.readLine()) % 42] = true;
        }

        int output = 0;

        for (boolean remainder : remainders) {
            if (remainder) {
                output++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}