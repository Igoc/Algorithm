import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    public int number;
    public int depth;
    public List<Student> tallerStudents;

    public Student(int number) {
        this.number = number;
        this.depth = 0;
        this.tallerStudents = new ArrayList<>();
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(depth, o.depth);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Student[] students = new Student[N];

        for (int index = 0; index < N; index++) {
            students[index] = new Student(index + 1);
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;

            students[A].tallerStudents.add(students[B]);

            Queue<Student> queue = new LinkedList<>();

            students[B].depth = Math.max(students[B].depth, students[A].depth + 1);
            queue.offer(students[B]);

            while (!queue.isEmpty()) {
                Student student = queue.poll();

                for (Student tallerStudent : student.tallerStudents) {
                    if (tallerStudent.depth < student.depth + 1) {
                        tallerStudent.depth = student.depth + 1;
                        queue.offer(tallerStudent);
                    }
                }
            }
        }

        Arrays.sort(students);

        for (Student student : students) {
            out.write(student.number + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}