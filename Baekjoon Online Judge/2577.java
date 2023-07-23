import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int A = Integer.parseInt(in.readLine());
        int B = Integer.parseInt(in.readLine());
        int C = Integer.parseInt(in.readLine());

        String product = String.valueOf(A * B * C);
        int[] digits = new int[10];

        for (int index = 0; index < product.length(); index++) {
            digits[product.charAt(index) - '0']++;
        }

        for (int digit : digits) {
            out.write(digit + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}