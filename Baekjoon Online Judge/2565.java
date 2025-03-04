import java.io.*;
import java.util.*;

class ElectricWire {
    public int left;
    public int right;

    public ElectricWire(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(in.readLine());

        ElectricWire[] electricWires = new ElectricWire[number];

        for (int index = 0; index < number; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int left = Integer.parseInt(tokenizer.nextToken());
            int right = Integer.parseInt(tokenizer.nextToken());

            electricWires[index] = new ElectricWire(left, right);
        }

        Arrays.sort(electricWires, Comparator.comparingInt(o -> o.left));

        List<Integer> longestIncreasingSubsequence = new ArrayList<>(List.of(electricWires[0].right));

        for (int index = 1; index < number; index++) {
            if (electricWires[index].right > longestIncreasingSubsequence.get(longestIncreasingSubsequence.size() - 1)) {
                longestIncreasingSubsequence.add(electricWires[index].right);
            } else {
                longestIncreasingSubsequence.set(-Collections.binarySearch(longestIncreasingSubsequence, electricWires[index].right) - 1, electricWires[index].right);
            }
        }

        out.write(String.valueOf(number - longestIncreasingSubsequence.size()));

        in.close();
        out.flush();
        out.close();
    }
}