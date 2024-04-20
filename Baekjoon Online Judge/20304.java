import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        int[] p = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Queue<Integer> queue = new LinkedList<>();
        int[] security = new int[N + 1];
        int output = 0;

        Arrays.fill(security, -1);

        for (int password : p) {
            queue.offer(password);
            security[password] = 0;
        }

        while (!queue.isEmpty()) {
            int password = queue.poll();

            for (int bit = 1; bit <= N; bit <<= 1) {
                if ((password ^ bit) <= N && security[password ^ bit] == -1) {
                    queue.offer(password ^ bit);
                    security[password ^ bit] = security[password] + 1;
                    output = Math.max(output, security[password ^ bit]);
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}