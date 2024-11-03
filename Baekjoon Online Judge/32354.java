import java.io.*;
import java.util.StringTokenizer;

class Deck {
    public long sum;
    public int penultimatePushSequence;
    public int lastPushSequence;

    public Deck(long sum, int penultimatePushSequence, int lastPushSequence) {
        this.sum = sum;
        this.penultimatePushSequence = penultimatePushSequence;
        this.lastPushSequence = lastPushSequence;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Deck[] decks = new Deck[N + 1];
        decks[0] = new Deck(0L, 0, 0);

        for (int sequence = 1; sequence <= N; sequence++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            String action = tokenizer.nextToken();

            switch (action) {
                case "push":
                    long x = Long.parseLong(tokenizer.nextToken());
                    decks[sequence] = new Deck(decks[sequence - 1].sum + x, decks[sequence - 1].lastPushSequence, sequence);
                    break;

                case "pop":
                    decks[sequence] = decks[decks[sequence - 1].penultimatePushSequence];
                    break;

                case "restore":
                    int i = Integer.parseInt(tokenizer.nextToken());
                    decks[sequence] = decks[i];
                    break;

                case "print":
                    decks[sequence] = decks[sequence - 1];
                    out.write(decks[sequence].sum + "\n");
                    break;
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}