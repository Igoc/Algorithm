import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] tallerPeopleNumber = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] outputs = new int[N];

        for (int height = 1; height <= N; height++) {
            int count = 0;

            for (int index = 0; index < N; index++) {
                if (outputs[index] == 0) {
                    count++;
                }

                if (count == tallerPeopleNumber[height - 1] + 1) {
                    outputs[index] = height;
                    break;
                }
            }
        }

        for (int height : outputs) {
            out.write(height + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}