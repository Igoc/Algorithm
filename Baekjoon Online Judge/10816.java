import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        String[] numberCards = in.readLine().split(" ");

        Map<Integer, Integer> numberCount = new HashMap<>();

        for (String numberCard : numberCards) {
            int number = Integer.parseInt(numberCard);

            if (!numberCount.containsKey(number)) {
                numberCount.put(number, 0);
            }

            int count = numberCount.get(number);

            numberCount.replace(number, count + 1);
        }

        int M = Integer.parseInt(in.readLine());
        String[] integers = in.readLine().split(" ");

        for (String integer : integers) {
            int number = Integer.parseInt(integer);

            if (numberCount.containsKey(number)) {
                out.write(String.valueOf(numberCount.get(number)) + ' ');
            } else {
                out.write("0 ");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}