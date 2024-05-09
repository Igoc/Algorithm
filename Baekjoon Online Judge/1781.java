import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Problem {
    public int deadline;
    public int cupRamen;

    public Problem(int deadline, int cupRamen) {
        this.deadline = deadline;
        this.cupRamen = cupRamen;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Problem[] problems = new Problem[N];
        int[] times = new int[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int deadline = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cupRamen = Integer.parseInt(tokenizer.nextToken());

            problems[index] = new Problem(deadline, cupRamen);
            times[index] = index;
        }

        Arrays.sort(problems, Comparator.<Problem>comparingInt(o -> o.cupRamen).reversed());

        int output = 0;

        for (Problem problem : problems) {
            int time = findTime(problem.deadline, times);

            if (time != -1) {
                times[time]--;
                output += problem.cupRamen;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static int findTime(int deadline, int[] times) {
        if (deadline == -1 || times[deadline] == deadline) {
            return deadline;
        }

        return times[deadline] = findTime(times[deadline], times);
    }
}