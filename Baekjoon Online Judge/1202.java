import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewellery {
    public int mass;
    public int value;

    public Jewellery(int mass, int value) {
        this.mass = mass;
        this.value = value;
    }

    public int getMass() {
        return mass;
    }

    public int getValue() {
        return value;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Jewellery[] jewelleries = new Jewellery[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int M = Integer.parseInt(tokenizer.nextToken());
            int V = Integer.parseInt(tokenizer.nextToken());

            jewelleries[index] = new Jewellery(M, V);
        }

        Arrays.sort(jewelleries, Comparator.comparingInt(Jewellery::getMass));

        int[] bags = new int[K];

        for (int index = 0; index < K; index++) {
            bags[index] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(bags);

        PriorityQueue<Jewellery> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Jewellery::getValue).reversed());
        int jewelleryIndex = 0;
        long output = 0L;

        for (int bag : bags) {
            while (jewelleryIndex < N && jewelleries[jewelleryIndex].mass <= bag) {
                priorityQueue.offer(jewelleries[jewelleryIndex++]);
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