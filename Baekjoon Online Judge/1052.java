import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int litre = 1 << 23;

        for (int bottle = 0; bottle < K - 1; bottle++) {
            while (litre > N) {
                litre >>= 1;
            }

            N -= litre;
        }

        if (N == 0) {
            out.write("0");
        } else {
            litre = 1;

            while (N > litre) {
                litre <<= 1;
            }

            out.write(String.valueOf(litre - N));
        }

        in.close();
        out.flush();
        out.close();
    }
}