import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        String output = Arrays.stream(in.readLine().split(" "))
                .sorted((o1, o2) -> {
                    String x = o1 + o2;
                    String y = o2 + o1;

                    for (int index = 0; index < x.length(); index++) {
                        if (x.charAt(index) != y.charAt(index)) {
                            return y.charAt(index) - x.charAt(index);
                        }
                    }

                    return 0;
                }).collect(Collectors.joining());

        if (output.charAt(0) == '0') {
            out.write("0");
        } else {
            out.write(output);
        }

        in.close();
        out.flush();
        out.close();
    }
}