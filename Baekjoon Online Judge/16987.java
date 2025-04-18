import java.io.*;
import java.util.StringTokenizer;

class Egg {
    public int durability;
    public int weight;

    public Egg(int durability, int weight) {
        this.durability = durability;
        this.weight = weight;
    }

    public Egg(Egg egg) {
        this.durability = egg.durability;
        this.weight = egg.weight;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Egg[] eggs = new Egg[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int S = Integer.parseInt(tokenizer.nextToken());
            int W = Integer.parseInt(tokenizer.nextToken());

            eggs[index] = new Egg(S, W);
        }

        out.write(String.valueOf(calculateCrackedEggMaximumNumber(eggs, 0, 0, 0)));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateCrackedEggMaximumNumber(Egg[] eggs, int current, int crackedEggNumber, int crackedEggMaximumNumber) {
        if (current >= eggs.length) {
            return Math.max(crackedEggNumber, crackedEggMaximumNumber);
        }

        if (eggs[current].durability <= 0 || crackedEggNumber == eggs.length - 1) {
            return calculateCrackedEggMaximumNumber(eggs, current + 1, crackedEggNumber, crackedEggMaximumNumber);
        }

        for (int other = 0; other < eggs.length; other++) {
            if (current != other && eggs[other].durability > 0) {
                Egg currentEgg = new Egg(eggs[current]);
                Egg otherEgg = new Egg(eggs[other]);
                int nextCrackedEggNumber = crackedEggNumber;

                eggs[current].durability -= eggs[other].weight;
                eggs[other].durability -= eggs[current].weight;

                if (eggs[current].durability <= 0) {
                    nextCrackedEggNumber++;
                }

                if (eggs[other].durability <= 0) {
                    nextCrackedEggNumber++;
                }

                crackedEggMaximumNumber = calculateCrackedEggMaximumNumber(eggs, current + 1, nextCrackedEggNumber, crackedEggMaximumNumber);
                eggs[current] = currentEgg;
                eggs[other] = otherEgg;
            }
        }

        return crackedEggMaximumNumber;
    }
}