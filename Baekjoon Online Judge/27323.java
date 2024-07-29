import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int A = Integer.parseInt(in.readLine());
        int B = Integer.parseInt(in.readLine());

        out.write(String.valueOf(A * B));

        in.close();
        out.flush();
        out.close();
    }
}