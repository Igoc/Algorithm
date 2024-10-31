import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> tallerStudents = new ArrayList<>();
        int[] shorterStudentNumbers = new int[N];

        for (int student = 0; student < N; student++) {
            tallerStudents.add(new ArrayList<>());
        }

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;

            tallerStudents.get(A).add(B);
            shorterStudentNumbers[B]++;
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int student = 0; student < N; student++) {
            if (shorterStudentNumbers[student] == 0) {
                shorterStudentNumbers[student] = -1;
                deque.offer(student);
            }
        }

        while (!deque.isEmpty()) {
            int student = deque.poll();

            for (int tallerStudent : tallerStudents.get(student)) {
                shorterStudentNumbers[tallerStudent]--;

                if (shorterStudentNumbers[tallerStudent] == 0) {
                    shorterStudentNumbers[tallerStudent] = -1;
                    deque.offer(tallerStudent);
                }
            }

            out.write((student + 1) + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}