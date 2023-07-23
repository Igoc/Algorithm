import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String S = in.readLine();

        int[] alphabets = new int[26];

        Arrays.fill(alphabets, -1);

        for (int index = 0; index < S.length(); index++) {
            int alphabet = S.charAt(index) - 'a';

            if (alphabets[alphabet] == -1) {
                alphabets[alphabet] = index;
            }
        }

        for (int alphabet : alphabets) {
            out.write(alphabet + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}