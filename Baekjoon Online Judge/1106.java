import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class City {
    public int cost;
    public int customer;

    public City(int cost, int customer) {
        this.cost = cost;
        this.customer = customer;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int C = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        City[] cities = new City[N];
        int[] costs = new int[C + 101];

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int cost = Integer.parseInt(tokenizer.nextToken());
            int customer = Integer.parseInt(tokenizer.nextToken());

            cities[index] = new City(cost, customer);

            for (int multiple = 1; customer * multiple <= C + 100; multiple++) {
                costs[customer * multiple] = Math.min(costs[customer * multiple], cost * multiple);
            }
        }

        for (City city : cities) {
            for (int customer = 0; customer <= C + 100; customer++) {
                if (customer - city.customer >= 0 && costs[customer - city.customer] != Integer.MAX_VALUE) {
                    costs[customer] = Math.min(costs[customer], costs[customer - city.customer] + city.cost);
                }
            }
        }

        int output = Integer.MAX_VALUE;

        for (int customer = C; customer <= C + 100; customer++) {
            output = Math.min(output, costs[customer]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}