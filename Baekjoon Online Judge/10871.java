import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int X = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(in.readLine());

        for (int index = 0; index < N; index++) {
            int number = Integer.parseInt(tokenizer.nextToken());

            if (number < X) {
                out.write(number + " ");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}