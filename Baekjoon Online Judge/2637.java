import java.io.*;
import java.util.*;

class Accessory {
    public int type;
    public int requiredNumber;

    public Accessory(int type, int requiredNumber) {
        this.type = type;
        this.requiredNumber = requiredNumber;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        List<List<Accessory>> accessories = new ArrayList<>();
        int[] precedingPartNumbers = new int[N];

        for (int part = 0; part < N; part++) {
            accessories.add(new ArrayList<>());
        }

        for (int relationship = 0; relationship < M; relationship++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int X = Integer.parseInt(tokenizer.nextToken()) - 1;
            int Y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int K = Integer.parseInt(tokenizer.nextToken());

            accessories.get(X).add(new Accessory(Y, K));
            precedingPartNumbers[Y]++;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] partNumbers = new int[N];

        deque.offer(N - 1);
        partNumbers[N - 1] = 1;

        while (!deque.isEmpty()) {
            int part = deque.poll();

            for (Accessory accessory : accessories.get(part)) {
                partNumbers[accessory.type] += partNumbers[part] * accessory.requiredNumber;
                precedingPartNumbers[accessory.type]--;

                if (precedingPartNumbers[accessory.type] == 0) {
                    deque.offer(accessory.type);
                }
            }
        }

        for (int part = 0; part < N; part++) {
            if (accessories.get(part).isEmpty()) {
                out.write((part + 1) + " " + partNumbers[part] + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}