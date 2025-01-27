import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Math.min(Integer.parseInt(tokenizer.nextToken()), N >> 1);
        String S = in.readLine();

        int x = 0;
        int y = 0;
        String output = "NO";

        for (int repeat = 0; repeat < K; repeat++) {
            for (char direction : S.toCharArray()) {
                if (direction == 'U') {
                    y--;
                } else if (direction == 'D') {
                    y++;
                } else if (direction == 'L') {
                    x--;
                } else if (direction == 'R') {
                    x++;
                }

                if (x == 0 && y == 0) {
                    output = "YES";
                    break;
                }
            }
        }

        out.write(output);

        in.close();
        out.flush();
        out.close();
    }
}