import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        int output = 1;

        while (A < B) {
            if (B % 10 == 1) {
                B /= 10;
            } else if (B % 2 == 0) {
                B /= 2;
            } else {
                break;
            }

            output++;
        }

        if (A == B) {
            out.write(String.valueOf(output));
        } else {
            out.write("-1");
        }

        in.close();
        out.flush();
        out.close();
    }
}