import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Integer> P = Arrays.stream(in.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        int cumulativeSum = 0;
        int output = 0;

        for (int index = 0; index < N; index++) {
            cumulativeSum += P.get(index);
            output += cumulativeSum;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}