import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] towers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Stack<Integer> stack = new Stack<>();
        int[] outputs = new int[N];

        for (int index = N - 1; index >= 0; index--) {
            while (!stack.isEmpty() && towers[stack.peek()] <= towers[index]) {
                outputs[stack.pop()] = index + 1;
            }

            stack.push(index);
        }

        for (int output : outputs) {
            out.write(output + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}