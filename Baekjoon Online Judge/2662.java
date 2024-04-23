import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] companies = new int[M + 1][N + 1];

        for (int investment = 1; investment <= N; investment++) {
            tokenizer = new StringTokenizer(in.readLine());
            tokenizer.nextToken();

            for (int company = 1; company <= M; company++) {
                companies[company][investment] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int[][] profits = new int[M + 1][N + 1];
        int[][][] investments = new int[M + 1][N + 1][M + 1];

        for (int company = 1; company <= M; company++) {
            for (int money = 1; money <= N; money++) {
                profits[company][money] = profits[company - 1][money];
                investments[company][money] = investments[company - 1][money].clone();
            }

            for (int investment = 1; investment <= N; investment++) {
                for (int money = investment; money <= N; money++) {
                    if (profits[company][money] < profits[company - 1][money - investment] + companies[company][investment]) {
                        profits[company][money] = profits[company - 1][money - investment] + companies[company][investment];
                        investments[company][money] = investments[company - 1][money - investment].clone();
                        investments[company][money][company] = investment;
                    }
                }
            }
        }

        out.write(profits[M][N] + "\n");

        for (int company = 1; company <= M; company++) {
            out.write(investments[M][N][company] + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}