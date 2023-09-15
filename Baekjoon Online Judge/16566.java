import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] cards = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int[] cheolsuCards = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] cardIndexes = IntStream.range(0, M)
                .toArray();

        for (int cheolsuCard : cheolsuCards) {
            int leftIndex = 0;
            int rightIndex = M - 1;

            while (leftIndex <= rightIndex) {
                int index = (leftIndex + rightIndex) / 2;

                if (cards[cardIndexes[index]] <= cheolsuCard) {
                    leftIndex = index + 1;
                } else {
                    rightIndex = index - 1;
                }
            }

            int index = 0;

            if (cards[cardIndexes[leftIndex]] < cheolsuCard || cardIndexes[leftIndex] >= M) {
                index = findCard(0, cardIndexes);
            } else {
                index = findCard(leftIndex, cardIndexes);
            }

            out.write(cards[index] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }

    public static int findCard(int index, int[] cardIndexes) {
        if (cardIndexes[index] == index) {
            cardIndexes[index] = index + 1;

            return index;
        }

        cardIndexes[index] = findCard(cardIndexes[index], cardIndexes);

        return cardIndexes[index];
    }
}