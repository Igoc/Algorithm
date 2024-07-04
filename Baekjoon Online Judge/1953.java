import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Student {
    public int index;
    public int[] haters;
    public int team;

    public Student(int index, int[] haters) {
        this.index = index;
        this.haters = haters;
        this.team = -1;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        Student[] students = new Student[n];

        for (int index = 0; index < n; index++) {
            int[] haters = Arrays.stream(in.readLine().split(" "))
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .map(student -> student - 1)
                    .toArray();

            students[index] = new Student(index, haters);
        }

        Stack<Integer> stack = new Stack<>();

        for (Student student : students) {
            if (student.team != -1) {
                continue;
            }

            student.team = 0;
            stack.push(student.index);

            while (!stack.isEmpty()) {
                int index = stack.pop();

                for (int hater : students[index].haters) {
                    if (students[hater].team != -1) {
                        continue;
                    }

                    students[hater].team = (students[index].team + 1) % 2;
                    stack.push(hater);
                }
            }
        }

        List<Integer> blueTeam = new ArrayList<>();
        List<Integer> whiteTeam = new ArrayList<>();

        for (Student student : students) {
            if (student.team == 0) {
                blueTeam.add(student.index + 1);
            } else {
                whiteTeam.add(student.index + 1);
            }
        }

        out.write(blueTeam.size() + "\n");

        for (int index : blueTeam) {
            out.write(index + " ");
        }

        out.write("\n" + whiteTeam.size() + "\n");

        for (int index : whiteTeam) {
            out.write(index + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}