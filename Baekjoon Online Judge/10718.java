import java.io.*;

public class Main {
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        out.write("강한친구 대한육군\n강한친구 대한육군");

        out.flush();
        out.close();
    }
}