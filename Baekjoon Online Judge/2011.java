import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String password = in.readLine();

        int[][] cases = new int[password.length()][2];

        if (password.charAt(0) != '0') {
            cases[0][0] = 1;
        }

        for (int index = 1; index < password.length(); index++) {
            int previousDigit = password.charAt(index - 1) - '0';
            int currentDigit = password.charAt(index) - '0';

            if (currentDigit != 0) {
                cases[index][0] = (cases[index - 1][0] + cases[index - 1][1]) % 1000000;
            }

            if (previousDigit != 0 && previousDigit * 10 + currentDigit <= 26) {
                cases[index][1] = cases[index - 1][0];
            }
        }

        out.write(String.valueOf((cases[password.length() - 1][0] + cases[password.length() - 1][1]) % 1000000));

        in.close();
        out.flush();
        out.close();
    }
}