import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> nextProblems = new ArrayList<>();
        int[] previousProblemNumbers = new int[N];

        for (int problem = 0; problem < N; problem++) {
            nextProblems.add(new ArrayList<>());
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;

            nextProblems.get(A).add(B);
            previousProblemNumbers[B]++;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int problem = 0; problem < N; problem++) {
            if (previousProblemNumbers[problem] == 0) {
                priorityQueue.offer(problem);
            }
        }

        while (!priorityQueue.isEmpty()) {
            int problem = priorityQueue.poll();

            out.write((problem + 1) + " ");

            for (int nextProblem : nextProblems.get(problem)) {
                previousProblemNumbers[nextProblem]--;

                if (previousProblemNumbers[nextProblem] == 0) {
                    priorityQueue.offer(nextProblem);
                }
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}