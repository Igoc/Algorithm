import java.io.*;
import java.util.StringTokenizer;

class Item {
    public int weight;
    public int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Item[] items = new Item[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int W = Integer.parseInt(tokenizer.nextToken());
            int V = Integer.parseInt(tokenizer.nextToken());

            items[index] = new Item(W, V);
        }

        int[] bags = new int[K + 1];

        for (int item = 0; item < N; item++) {
            for (int bag = K; bag >= 0; bag--) {
                if (bag - items[item].weight >= 0) {
                    bags[bag] = Math.max(bags[bag], bags[bag - items[item].weight] + items[item].value);
                }
            }
        }

        out.write(String.valueOf(bags[K]));

        in.close();
        out.flush();
        out.close();
    }
}