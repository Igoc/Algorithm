import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[] ports = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> lis = new ArrayList<>(List.of(ports[0]));

        for (int index = 1; index < n; index++) {
            if (ports[index] > lis.get(lis.size() - 1)) {
                lis.add(ports[index]);
            } else {
                lis.set(-Collections.binarySearch(lis, ports[index]) - 1, ports[index]);
            }
        }

        out.write(String.valueOf(lis.size()));

        in.close();
        out.flush();
        out.close();
    }
}