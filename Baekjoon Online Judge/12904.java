import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String S = in.readLine();
        StringBuilder T = new StringBuilder(in.readLine());

        while (S.length() < T.length()) {
            char lastCharacter = T.charAt(T.length() - 1);

            T.deleteCharAt(T.length() - 1);

            if (lastCharacter == 'B') {
                T = T.reverse();
            }
        }

        out.write((S.equals(T.toString())) ? ("1") : ("0"));

        in.close();
        out.flush();
        out.close();
    }
}