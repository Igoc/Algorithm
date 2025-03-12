import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] numbers = new int[N];

        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(in.readLine());
        }

        List<Integer> lis = new ArrayList<>(List.of(numbers[0]));

        for (int index = 1; index < N; index++) {
            if (numbers[index] > lis.get(lis.size() - 1)) {
                lis.add(numbers[index]);
            } else {
                lis.set(-Collections.binarySearch(lis, numbers[index]) - 1, numbers[index]);
            }
        }

        out.write(String.valueOf(N - lis.size()));

        in.close();
        out.flush();
        out.close();
    }
}