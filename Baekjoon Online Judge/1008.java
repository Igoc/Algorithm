import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        double A = Double.parseDouble(tokenizer.nextToken());
        double B = Double.parseDouble(tokenizer.nextToken());

        out.write(String.format("%.9f", A / B));

        in.close();
        out.flush();
        out.close();
    }
}