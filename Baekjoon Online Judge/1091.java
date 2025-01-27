import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] P = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] S = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] deck = new int[N];
        long[] initialCards = new long[3];
        long[] targetCards = new long[3];

        for (int index = 0; index < N; index++) {
            deck[index] = index;
            initialCards[index % 3] |= 1L << index;
            targetCards[P[index]] |= 1L << index;
        }

        long[] cards = initialCards.clone();
        int output = 0;

        while (true) {
            if (Arrays.equals(cards, targetCards)) {
                break;
            }

            deck = shuffle(deck, S);
            cards = distribute(deck);
            output++;

            if (Arrays.equals(cards, initialCards)) {
                output = -1;
                break;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static int[] shuffle(int[] deck, int[] method) {
        int[] result = new int[deck.length];

        for (int index = 0; index < deck.length; index++) {
            result[method[index]] = deck[index];
        }

        return result;
    }

    public static long[] distribute(int[] deck) {
        long[] result = new long[3];

        for (int index = 0; index < deck.length; index++) {
            result[index % 3] |= 1L << deck[index];
        }

        return result;
    }
}