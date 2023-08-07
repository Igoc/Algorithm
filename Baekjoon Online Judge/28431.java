import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] socks = new int[10];

        for (int index = 0; index < 5; index++) {
            socks[Integer.parseInt(in.readLine())]++;
        }

        for (int index = 0; index < 10; index++) {
            if (socks[index] % 2 == 1) {
                out.write(String.valueOf(index));
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}