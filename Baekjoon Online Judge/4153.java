import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            int[] lengths = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            if ((lengths[0] | lengths[1] | lengths[2]) == 0) {
                break;
            }

            lengths[0] = (int) Math.pow(lengths[0], 2);
            lengths[1] = (int) Math.pow(lengths[1], 2);
            lengths[2] = (int) Math.pow(lengths[2], 2);

            out.write((lengths[0] + lengths[1] == lengths[2]) ? ("right\n") : ("wrong\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}