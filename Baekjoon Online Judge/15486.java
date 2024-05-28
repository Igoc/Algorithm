import java.io.*;
import java.util.StringTokenizer;

class Counsel {
    public int requiredDate;
    public int reward;

    public Counsel(int requiredDate, int reward) {
        this.requiredDate = requiredDate;
        this.reward = reward;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Counsel[] counsels = new Counsel[N];

        for (int date = 0; date < N; date++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int T = Integer.parseInt(tokenizer.nextToken());
            int P = Integer.parseInt(tokenizer.nextToken());

            counsels[date] = new Counsel(T, P);
        }

        int[] rewards = new int[N + 1];

        for (int date = 0; date < N; date++) {
            if (date > 0) {
                rewards[date] = Math.max(rewards[date], rewards[date - 1]);
            }

            if (date + counsels[date].requiredDate <= N) {
                rewards[date + counsels[date].requiredDate] = Math.max(rewards[date + counsels[date].requiredDate], rewards[date] + counsels[date].reward);
            }
        }

        out.write(String.valueOf(Math.max(rewards[N - 1], rewards[N])));

        in.close();
        out.flush();
        out.close();
    }
}