import java.io.*;
import java.util.*;

class Jewel implements Comparable<Jewel> {
    public int weight;
    public int value;

    public Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Jewel o) {
        return value - o.value;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        List<Jewel> jewels = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int M = Integer.parseInt(tokenizer.nextToken());
            int V = Integer.parseInt(tokenizer.nextToken());

            jewels.add(new Jewel(M, V));
        }

        jewels.sort(Comparator.comparingInt(o -> o.weight));

        List<Integer> bags = new ArrayList<>();

        for (int index = 0; index < K; index++) {
            int C = Integer.parseInt(in.readLine());

            bags.add(C);
        }

        bags.sort(Comparator.naturalOrder());

        PriorityQueue<Jewel> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int jewelIndex = 0;
        long output = 0;

        for (int bagIndex = 0; bagIndex < K; bagIndex++) {
            while (jewelIndex < N && jewels.get(jewelIndex).weight <= bags.get(bagIndex)) {
                priorityQueue.add(jewels.get(jewelIndex));
                jewelIndex++;
            }

            if (!priorityQueue.isEmpty()) {
                output += priorityQueue.poll().value;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}