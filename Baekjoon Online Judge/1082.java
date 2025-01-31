import java.io.*;
import java.util.Arrays;

class Number implements Comparable<Number> {
    public int value;
    public int cost;

    public Number(int value, int cost) {
        this.value = value;
        this.cost = cost;
    }

    @Override
    public int compareTo(Number o) {
        if (cost == o.cost) {
            return Integer.compare(o.value, value);
        }

        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] P = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int M = Integer.parseInt(in.readLine());

        Number[] numbers = new Number[N];

        for (int value = 0; value < N; value++) {
            numbers[value] = new Number(value, P[value]);
        }

        Arrays.sort(numbers);

        StringBuilder output = new StringBuilder();

        for (int index = 0; index < N; index++) {
            while (numbers[index].cost <= M) {
                M -= numbers[index].cost;
                output.append(numbers[index].value);

                if (index < N - 1 && numbers[index].value == 0 && numbers[index + 1].cost > M - numbers[index].cost) {
                    break;
                }
            }
        }

        int index = output.length() - 1;

        for (int value = N - 1; value >= 0; value--) {
            while (index >= 0 && P[value] <= M + P[output.charAt(index) - '0']) {
                M += P[output.charAt(index) - '0'] - P[value];
                output.setCharAt(index, (char) (value + '0'));
                index--;
            }
        }

        if (output.charAt(output.length() - 1) == '0') {
            out.write('0');
        } else {
            out.write(output.reverse().toString());
        }

        in.close();
        out.flush();
        out.close();
    }
}