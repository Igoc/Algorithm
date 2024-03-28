import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] budgetRequests = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int M = Integer.parseInt(in.readLine());

        int minimumBudget = 0;
        int maximumBudget = M;

        while (minimumBudget <= maximumBudget) {
            int budget = (minimumBudget + maximumBudget) / 2;

            int sum = 0;

            for (int budgetRequest : budgetRequests) {
                sum += Math.min(budgetRequest, budget);
            }

            if (sum <= M) {
                minimumBudget = budget + 1;
            } else {
                maximumBudget = budget - 1;
            }
        }

        int budget = 0;

        for (int budgetRequest : budgetRequests) {
            budget = Math.max(budget, Math.min(budgetRequest, maximumBudget));
        }

        out.write(String.valueOf(budget));

        in.close();
        out.flush();
        out.close();
    }
}