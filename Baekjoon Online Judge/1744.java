import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Integer> sequence = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            sequence.add(Integer.parseInt(in.readLine()));
        }

        sequence.sort((o1, o2) -> {
            if (o1 <= 0 && o2 <= 0) {
                return o1 - o2;
            }

            return o2 - o1;
        });

        int output = 0;

        for (int index = 0; index < N; index++) {
            if (index + 1 == N) {
                output += sequence.get(index);

                break;
            }

            int x = sequence.get(index);
            int y = sequence.get(index + 1);

            if (x > 1 && y > 1 || x <= 0 && y <= 0) {
                output += x * y;
                index++;
            } else {
                output += x;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}