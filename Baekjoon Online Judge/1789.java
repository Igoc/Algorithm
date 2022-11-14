import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        long S = Long.parseLong(in.readLine());

        long number = 1;

        while (S - number > number) {
            S -= number;
            number++;
        }

        out.write(String.valueOf(number));

        in.close();
        out.flush();
        out.close();
    }
}