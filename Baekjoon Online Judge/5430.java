import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            String p = in.readLine();
            int n = Integer.parseInt(in.readLine());
            String array = in.readLine();

            Deque<String> deque = new ArrayDeque<>(Arrays.asList(array.substring(1, array.length() - 1).split(",")));
            boolean flip = false;
            boolean error = false;

            for (int index = 0; index < p.length(); index++) {
                if (p.charAt(index) == 'R') {
                    flip = !flip;
                } else if (n != 0 && !deque.isEmpty()) {
                    if (flip) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                } else {
                    error = true;

                    break;
                }
            }

            if (error) {
                out.write("error\n");
            } else {
                List<String> list = new ArrayList<>(deque);

                if (flip) {
                    Collections.reverse(list);
                }

                out.write(String.valueOf(list).replace(" ", "") + '\n');
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}