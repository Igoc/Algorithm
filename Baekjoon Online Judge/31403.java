import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int A = Integer.parseInt(in.readLine());
        int B = Integer.parseInt(in.readLine());
        int C = Integer.parseInt(in.readLine());

        out.write(A + B - C + "\n");
        out.write(String.valueOf(Integer.parseInt(A + "" + B) - C));

        in.close();
        out.flush();
        out.close();
    }
}