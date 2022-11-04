import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Set<String> words = new HashSet<>();

        for (int index = 0; index < N; index++) {
            words.add(in.readLine());
        }

        List<String> outputs = new ArrayList<>(words);

        outputs.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }

            return o1.compareTo(o2);
        });

        for (String output : outputs) {
            out.write(output + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}