import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Ingredient {
    public int spoilage;
    public int expiration;
    public boolean importance;

    public Ingredient(int spoilage, int expiration, boolean importance) {
        this.spoilage = spoilage;
        this.expiration = expiration;
        this.importance = importance;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int G = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Ingredient[] ingredients = new Ingredient[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int S = Integer.parseInt(tokenizer.nextToken());
            int L = Integer.parseInt(tokenizer.nextToken());
            int O = Integer.parseInt(tokenizer.nextToken());

            ingredients[index] = new Ingredient(S, L, O == 0);
        }

        long minimumDate = 0L;
        long maximumDate = Integer.MAX_VALUE;

        while (minimumDate <= maximumDate) {
            long date = (minimumDate + maximumDate) / 2;

            PriorityQueue<Long> excludedGermNumbers = new PriorityQueue<>();
            long germNumber = 0L;

            for (Ingredient ingredient : ingredients) {
                long currentGermNumber = ingredient.spoilage * Math.max(1L, date - ingredient.expiration);

                if (ingredient.importance) {
                    germNumber += currentGermNumber;

                    continue;
                }

                if (excludedGermNumbers.size() < K) {
                    excludedGermNumbers.offer(currentGermNumber);
                } else if (!excludedGermNumbers.isEmpty() && excludedGermNumbers.peek() < currentGermNumber) {
                    excludedGermNumbers.offer(currentGermNumber);
                    germNumber += excludedGermNumbers.poll();
                } else {
                    germNumber += currentGermNumber;
                }
            }

            if (germNumber <= G) {
                minimumDate = date + 1;
            } else {
                maximumDate = date - 1;
            }
        }

        out.write(String.valueOf(maximumDate));

        in.close();
        out.flush();
        out.close();
    }
}