import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        String[] S = new String[N];

        Map<String, Integer> map = new HashMap<>();
        int hiddenStringIndex = 0;

        for (int index = 0; index < N; index++) {
            S[index] = in.readLine();
            map.put(S[index], index);

            if (S[index].equals("?")) {
                hiddenStringIndex = index;
            }
        }

        char previousCharacter = (hiddenStringIndex == 0) ? (0) : (S[hiddenStringIndex - 1].charAt(S[hiddenStringIndex - 1].length() - 1));
        char nextCharacter = (hiddenStringIndex == N - 1) ? (0) : (S[hiddenStringIndex + 1].charAt(0));

        int M = Integer.parseInt(in.readLine());

        for (int index = 0; index < M; index++) {
            String A = in.readLine();

            if (!map.containsKey(A)) {
                if ((previousCharacter == 0 || A.charAt(0) == previousCharacter) && (nextCharacter == 0 || A.charAt(A.length() - 1) == nextCharacter)) {
                    out.write(A);
                }
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}