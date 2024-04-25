import java.io.*;
import java.util.StringTokenizer;

class Coin {
    public int amount;
    public int number;

    public Coin(int amount, int number) {
        this.amount = amount;
        this.number = number;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        for (int testCase = 0; testCase < 3; testCase++) {
            int N = Integer.parseInt(in.readLine());

            Coin[] coins = new Coin[N];
            boolean[] possibles = new boolean[50001];
            int totalAmount = 0;

            for (int index = 0; index < N; index++) {
                StringTokenizer tokenizer = new StringTokenizer(in.readLine());

                int amount = Integer.parseInt(tokenizer.nextToken());
                int number = Integer.parseInt(tokenizer.nextToken());

                coins[index] = new Coin(amount, number);
                totalAmount += amount * number;

                while (number >= 0 && amount * number <= 50000) {
                    possibles[amount * number] = true;
                    number--;
                }
            }

            if (totalAmount % 2 == 1) {
                out.write("0\n");

                continue;
            }

            if (possibles[totalAmount / 2]) {
                out.write("1\n");

                continue;
            }

            possibles = new boolean[totalAmount / 2 + 1];
            possibles[0] = true;

            for (Coin coin : coins) {
                for (int money = totalAmount / 2; money >= 0; money--) {
                    if (!possibles[money]) {
                        continue;
                    }

                    for (int number = 1; number <= coin.number; number++) {
                        if (money + coin.amount * number <= totalAmount / 2) {
                            possibles[money + coin.amount * number] = true;
                        }
                    }
                }
            }

            out.write((possibles[totalAmount / 2]) ? ("1\n") : ("0\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}