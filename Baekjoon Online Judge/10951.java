import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            String input = in.readLine();

            if (input == null) {
                break;
            }

            int A = Integer.parseInt(input.split(" ")[0]);
            int B = Integer.parseInt(input.split(" ")[1]);

            out.write((A + B) + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}