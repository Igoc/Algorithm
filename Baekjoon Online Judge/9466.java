import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(in.readLine());

            int[] selectedStudents = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .map(student -> student - 1)
                    .toArray();

            int[] groups = new int[n];
            int[] sequences = new int[n];
            int output = 0;

            for (int student = 0; student < n; student++) {
                if (groups[student] != 0) {
                    continue;
                }

                Stack<Integer> stack = new Stack<>();

                stack.push(student);
                groups[student] = student + 1;
                sequences[student] = stack.size();

                while (true) {
                    int currentStudent = stack.peek();
                    int nextStudent = selectedStudents[currentStudent];

                    if (groups[nextStudent] != 0) {
                        if (groups[nextStudent] == student + 1) {
                            output += sequences[nextStudent] - 1;
                        } else {
                            output += sequences[currentStudent];
                        }

                        break;
                    }

                    stack.push(nextStudent);
                    groups[nextStudent] = student + 1;
                    sequences[nextStudent] = stack.size();
                }
            }

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}