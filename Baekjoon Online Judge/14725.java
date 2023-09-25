import java.io.*;
import java.util.*;

class Nest {
    public String food;
    public int depth;
    public Map<String, Nest> nextNests;

    public Nest(String food, int depth) {
        this.food = food;
        this.depth = depth;
        this.nextNests = new TreeMap<>(Comparator.reverseOrder());
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Nest entrance = new Nest(null, 0);

        for (int robotAnt = 0; robotAnt < N; robotAnt++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int K = Integer.parseInt(tokenizer.nextToken());

            Nest currentNest = entrance;

            for (int depth = 1; depth <= K; depth++) {
                String food = tokenizer.nextToken();

                if (!currentNest.nextNests.containsKey(food)) {
                    currentNest.nextNests.put(food, new Nest(food, depth));
                }

                currentNest = currentNest.nextNests.get(food);
            }
        }

        Stack<Nest> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (Nest nest : entrance.nextNests.values()) {
            stack.push(nest);
        }

        while (!stack.isEmpty()) {
            Nest currentNest = stack.pop();

            for (Nest nest : currentNest.nextNests.values()) {
                stack.push(nest);
            }

            output.append("--".repeat(currentNest.depth - 1))
                    .append(currentNest.food)
                    .append('\n');
        }

        out.write(output.toString());

        in.close();
        out.flush();
        out.close();
    }
}