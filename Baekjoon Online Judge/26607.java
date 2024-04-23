import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int x = Integer.parseInt(tokenizer.nextToken());

        int[] a = new int[n];

        for (int applicant = 0; applicant < n; applicant++) {
            a[applicant] = Integer.parseInt(in.readLine().split(" ")[0]);
        }

        boolean[][] table = new boolean[k + 1][k * x + 1];

        for (int applicant = 0; applicant < n; applicant++) {
            for (int member = k - 1; member >= 0; member--) {
                for (int power = 0; power <= k * x - a[applicant]; power++) {
                    table[member + 1][power + a[applicant]] |= table[member][power];
                }
            }

            table[1][a[applicant]] = true;
        }

        int output = 0;

        for (int power = 0; power <= k * x; power++) {
            if (table[k][power]) {
                output = Math.max(output, power * (k * x - power));
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}