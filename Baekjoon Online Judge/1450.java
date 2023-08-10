import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static List<Long> calculateWeightCombination(int index, int[] items, long sum, List<Long> weightCombination) {
        if (index >= items.length) {
            weightCombination.add(sum);

            return weightCombination;
        }

        weightCombination = calculateWeightCombination(index + 1, items, sum, weightCombination);
        weightCombination = calculateWeightCombination(index + 1, items, sum + items[index], weightCombination);

        return weightCombination;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        int[] items = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Long> leftWeightCombination = calculateWeightCombination(0, Arrays.copyOfRange(items, 0, N / 2), 0L, new ArrayList<>());
        List<Long> rightWeightCombination = calculateWeightCombination(0, Arrays.copyOfRange(items, N / 2, N), 0L, new ArrayList<>());

        rightWeightCombination.sort(Comparator.naturalOrder());

        int output = 0;

        for (long weight : leftWeightCombination) {
            int start = 0;
            int end = rightWeightCombination.size() - 1;

            while (start <= end) {
                int mid = (start + end) / 2;

                if (weight + rightWeightCombination.get(mid) <= C) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            output += start;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}