import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int sum = 0;
        int cube = 0;

        for (int number = 1; number <= N; number++) {
            sum += number;
            cube += number * number * number;
        }

        out.write(sum + "\n");
        out.write(sum * sum + "\n");
        out.write(cube + "\n");

        in.close();
        out.flush();
        out.close();
    }
}