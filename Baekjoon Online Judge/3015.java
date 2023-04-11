import java.io.*;
import java.util.Stack;

class Person {
    public int height;
    public long accumulatedCount;

    public Person(int height, long accumulatedCount) {
        this.height = height;
        this.accumulatedCount = accumulatedCount;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Stack<Person> stack = new Stack<>();
        long output = 0L;

        for (int index = 0; index < N; index++) {
            Person currentPerson = new Person(Integer.parseInt(in.readLine()), 0L);

            while (!stack.isEmpty()) {
                Person previousPerson = stack.peek();

                if (currentPerson.height > previousPerson.height) {
                    output += previousPerson.accumulatedCount + 1;
                } else if (currentPerson.height == previousPerson.height) {
                    currentPerson.accumulatedCount = previousPerson.accumulatedCount + 1;
                    output += currentPerson.accumulatedCount;
                } else {
                    output++;

                    break;
                }

                stack.pop();
            }

            stack.push(currentPerson);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}