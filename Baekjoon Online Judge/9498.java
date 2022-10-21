import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int score = Integer.parseInt(in.readLine());

        if (score >= 90 && score <= 100) {
            out.write("A");
        } else if (score >= 80 && score <= 89) {
            out.write("B");
        } else if (score >= 70 && score <= 79) {
            out.write("C");
        } else if (score >= 60 && score <= 69) {
            out.write("D");
        } else {
            out.write("F");
        }

        in.close();
        out.flush();
        out.close();
    }
}