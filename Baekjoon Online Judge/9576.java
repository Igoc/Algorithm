import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Student implements Comparable<Student> {
    public int start;
    public int end;

    public Student(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Student o) {
        if (end == o.end) {
            return Integer.compare(start, o.start);
        }

        return Integer.compare(end, o.end);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int testCaseNumber = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < testCaseNumber; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            Student[] students = new Student[M];

            for (int student = 0; student < M; student++) {
                tokenizer = new StringTokenizer(in.readLine());

                int a = Integer.parseInt(tokenizer.nextToken()) - 1;
                int b = Integer.parseInt(tokenizer.nextToken()) - 1;

                students[student] = new Student(a, b);
            }

            Arrays.sort(students);

            boolean[] books = new boolean[N];
            int output = 0;

            for (Student student : students) {
                for (int book = student.start; book <= student.end; book++) {
                    if (!books[book]) {
                        books[book] = true;
                        output++;

                        break;
                    }
                }
            }

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}