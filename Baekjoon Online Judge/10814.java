import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Member implements Comparable<Member> {
    private static int sequence = 0;

    public int id;
    public String name;
    public int age;

    public Member(String name, int age) {
        this.id = sequence++;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Member o) {
        if (age == o.age) {
            return Integer.compare(id, o.id);
        }

        return Integer.compare(age, o.age);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Member> members = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            String[] inputs = in.readLine().split(" ");

            members.add(new Member(inputs[1], Integer.parseInt(inputs[0])));
        }

        members.sort(Comparator.naturalOrder());

        for (Member member : members) {
            out.write(member.age + " " + member.name + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}