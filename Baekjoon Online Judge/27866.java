import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String S = in.readLine();
        int i = Integer.parseInt(in.readLine()) - 1;

        out.write(S.charAt(i));

        in.close();
        out.flush();
        out.close();
    }
}