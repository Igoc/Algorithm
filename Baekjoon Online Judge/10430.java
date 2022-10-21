import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        out.write(String.valueOf((A + B) % C) + '\n');
        out.write(String.valueOf(((A % C) + (B % C)) % C) + '\n');
        out.write(String.valueOf((A * B) % C) + '\n');
        out.write(String.valueOf(((A % C) * (B % C)) % C));

        in.close();
        out.flush();
        out.close();
    }
}