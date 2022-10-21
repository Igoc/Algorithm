import java.io.*;

public class Main {
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        out.write("Hello World!");

        out.flush();
        out.close();
    }
}