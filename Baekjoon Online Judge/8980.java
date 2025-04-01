import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Box {
    public int origin;
    public int destination;
    public int number;

    public Box(int origin, int destination, int number) {
        this.origin = origin;
        this.destination = destination;
        this.number = number;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(in.readLine());

        Box[] boxes = new Box[M];

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int origin = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destination = Integer.parseInt(tokenizer.nextToken()) - 1;
            int number = Integer.parseInt(tokenizer.nextToken());

            boxes[index] = new Box(origin, destination, number);
        }

        Arrays.sort(boxes, Comparator.comparingInt(o -> o.destination));

        int[] capacities = new int[N];
        int output = 0;

        Arrays.fill(capacities, C);

        for (Box box : boxes) {
            int number = box.number;

            for (int village = box.destination - 1; village >= box.origin; village--) {
                number = Math.min(number, capacities[village]);
            }

            for (int village = box.destination - 1; village >= box.origin; village--) {
                capacities[village] -= number;
            }

            output += number;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}