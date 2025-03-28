import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meat implements Comparable<Meat> {
    public int weight;
    public int price;

    public Meat(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Meat o) {
        if (price == o.price) {
            return Integer.compare(o.weight, weight);
        }

        return Integer.compare(price, o.price);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Meat[] meats = new Meat[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int weight = Integer.parseInt(tokenizer.nextToken());
            int price = Integer.parseInt(tokenizer.nextToken());

            meats[index] = new Meat(weight, price);
        }

        Arrays.sort(meats);

        int totalWeight = 0;
        int totalPrice = 0;
        int lastPrice = 0;
        int output = Integer.MAX_VALUE;

        for (Meat meat : meats) {
            totalWeight += meat.weight;

            if (meat.price > lastPrice) {
                totalPrice = lastPrice = meat.price;
            } else {
                totalPrice += meat.price;
            }

            if (totalWeight >= M) {
                output = Math.min(output, totalPrice);
            }
        }

        if (totalWeight < M) {
            output = -1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}