import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Integer> numbers = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            numbers.add(Integer.parseInt(in.readLine()));
        }

        numbers.sort(Comparator.naturalOrder());

        for (int index = 0; index < N; index++) {
            out.write(String.valueOf(numbers.get(index)) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}