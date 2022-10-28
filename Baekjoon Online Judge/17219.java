import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Map<String, String> sites = new HashMap<>();

        for (int index = 0; index < N; index++) {
            String[] inputs = in.readLine().split(" ");

            sites.put(inputs[0], inputs[1]);
        }

        for (int index = 0; index < M; index++) {
            out.write(sites.get(in.readLine()) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}