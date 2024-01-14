import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] c = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<List<Integer>> friends = new ArrayList<>();

        for (int child = 0; child < N; child++) {
            friends.add(new ArrayList<>());
        }

        for (int friend = 0; friend < M; friend++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;

            friends.get(a).add(b);
            friends.get(b).add(a);
        }

        List<Integer> candyNumbers = new ArrayList<>();
        List<Integer> cryingChildrenNumbers = new ArrayList<>();
        boolean[] visit = new boolean[N];

        for (int child = 0; child < N; child++) {
            if (visit[child]) {
                continue;
            }

            Deque<Integer> deque = new ArrayDeque<>();
            int candyNumber = 0;
            int cryingChildrenNumber = 0;

            deque.push(child);
            visit[child] = true;
            candyNumber += c[child];
            cryingChildrenNumber++;

            while (!deque.isEmpty()) {
                int currentChild = deque.pop();

                for (int friend : friends.get(currentChild)) {
                    if (visit[friend]) {
                        continue;
                    }

                    deque.push(friend);
                    visit[friend] = true;
                    candyNumber += c[friend];
                    cryingChildrenNumber++;
                }
            }

            candyNumbers.add(candyNumber);
            cryingChildrenNumbers.add(cryingChildrenNumber);
        }

        int[][] table = new int[K][candyNumbers.size() + 1];

        for (int cryingChildrenNumber = 0; cryingChildrenNumber < table.length; cryingChildrenNumber++) {
            for (int stealing = 1; stealing < table[cryingChildrenNumber].length; stealing++) {
                table[cryingChildrenNumber][stealing] = table[cryingChildrenNumber][stealing - 1];

                if (cryingChildrenNumber >= cryingChildrenNumbers.get(stealing - 1)) {
                    table[cryingChildrenNumber][stealing] = Math.max(
                            table[cryingChildrenNumber][stealing],
                            table[cryingChildrenNumber - cryingChildrenNumbers.get(stealing - 1)][stealing - 1] + candyNumbers.get(stealing - 1)
                    );
                }
            }
        }

        out.write(String.valueOf(table[K - 1][candyNumbers.size()]));

        in.close();
        out.flush();
        out.close();
    }
}