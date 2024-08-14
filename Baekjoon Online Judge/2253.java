import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        boolean[] smallStones = new boolean[N];

        for (int index = 0; index < M; index++) {
            smallStones[Integer.parseInt(in.readLine()) - 1] = true;
        }

        List<Map<Integer, Integer>> stones = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            stones.add(new HashMap<>());
        }

        stones.get(0).put(1, 0);

        if (!smallStones[1]) {
            stones.get(1).put(1, 1);
        }

        for (int index = 1; index < N; index++) {
            for (Map.Entry<Integer, Integer> state : stones.get(index).entrySet()) {
                int speed = state.getKey();
                int jumpNumber = state.getValue();

                for (int acceleration = -1; acceleration <= 1; acceleration++) {
                    int nextSpeed = speed + acceleration;
                    int nextIndex = index + nextSpeed;

                    if (nextSpeed > 0 && nextIndex < N && !smallStones[nextIndex]) {
                        Map<Integer, Integer> nextStone = stones.get(nextIndex);

                        if (!nextStone.containsKey(nextSpeed)) {
                            nextStone.put(nextSpeed, jumpNumber + 1);
                        } else if (jumpNumber + 1 < nextStone.get(nextSpeed)) {
                            nextStone.replace(nextSpeed, jumpNumber + 1);
                        }
                    }
                }
            }
        }

        int output = Integer.MAX_VALUE;

        for (int jumpNumber : stones.get(N - 1).values()) {
            output = Math.min(output, jumpNumber);
        }

        if (output == Integer.MAX_VALUE) {
            output = -1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}