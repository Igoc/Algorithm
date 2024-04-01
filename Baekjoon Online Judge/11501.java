import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class Stock implements Comparable<Stock> {
    public int date;
    public int price;

    public Stock(int date, int price) {
        this.date = date;
        this.price = price;
    }

    @Override
    public int compareTo(Stock o) {
        return Integer.compare(price, o.price);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(in.readLine());

            int[] stockPrices = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Stock[] stocks = new Stock[N];

            for (int date = 0; date < N; date++) {
                stocks[date] = new Stock(date, stockPrices[date]);
            }

            Arrays.sort(stocks, Comparator.reverseOrder());

            int date = 0;
            long money = 0L;

            for (Stock stock : stocks) {
                int stockNumber = 0;

                while (date < stock.date) {
                    if (stockPrices[date] < stock.price) {
                        stockNumber++;
                        money -= stockPrices[date];
                    }

                    date++;
                }

                money += (long) stock.price * (long) stockNumber;
            }

            out.write(money + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}