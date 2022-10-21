import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int year = Integer.parseInt(in.readLine());

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            out.write('1');
        } else {
            out.write('0');
        }

        in.close();
        out.flush();
        out.close();
    }
}