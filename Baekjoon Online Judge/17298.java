import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] NGE = new int[N];

        Stack<Integer> stack = new Stack<>();

        for (int index = 0; index < N; index++) {
            if (stack.isEmpty() || A[index] <= A[stack.peek()]) {
                stack.push(index);
            } else {
                while (!stack.isEmpty() && A[index] > A[stack.peek()]) {
                    NGE[stack.peek()] = A[index];
                    stack.pop();
                }

                stack.push(index);
            }
        }

        while (!stack.isEmpty()) {
            NGE[stack.peek()] = -1;
            stack.pop();
        }

        for (int index = 0; index < N; index++) {
            out.write(String.valueOf(NGE[index]) + ' ');
        }

        in.close();
        out.flush();
        out.close();
    }
}