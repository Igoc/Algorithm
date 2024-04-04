import java.io.*;
import java.util.Arrays;

public class Main {
    static final int MODULO_NUMBER = 1234567;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[][] keys = new int[2][10];
        int[] passwordNumbers = new int[1001];

        Arrays.fill(keys[1], 1);
        passwordNumbers[1] = 10;

        for (int length = 2; length <= 1000; length++) {
            keys[length % 2][0] = keys[(length - 1) % 2][7];
            keys[length % 2][1] = keys[(length - 1) % 2][2] + keys[(length - 1) % 2][4];
            keys[length % 2][2] = keys[(length - 1) % 2][1] + keys[(length - 1) % 2][3] + keys[(length - 1) % 2][5];
            keys[length % 2][3] = keys[(length - 1) % 2][2] + keys[(length - 1) % 2][6];
            keys[length % 2][4] = keys[(length - 1) % 2][1] + keys[(length - 1) % 2][5] + keys[(length - 1) % 2][7];
            keys[length % 2][5] = keys[(length - 1) % 2][2] + keys[(length - 1) % 2][4] + keys[(length - 1) % 2][6] + keys[(length - 1) % 2][8];
            keys[length % 2][6] = keys[(length - 1) % 2][3] + keys[(length - 1) % 2][5] + keys[(length - 1) % 2][9];
            keys[length % 2][7] = keys[(length - 1) % 2][0] + keys[(length - 1) % 2][4] + keys[(length - 1) % 2][8];
            keys[length % 2][8] = keys[(length - 1) % 2][5] + keys[(length - 1) % 2][7] + keys[(length - 1) % 2][9];
            keys[length % 2][9] = keys[(length - 1) % 2][6] + keys[(length - 1) % 2][8];

            for (int key = 0; key <= 9; key++) {
                keys[length % 2][key] %= MODULO_NUMBER;
                passwordNumbers[length] += keys[length % 2][key];
            }

            passwordNumbers[length] %= MODULO_NUMBER;
        }

        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(in.readLine());

            out.write(passwordNumbers[N] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}