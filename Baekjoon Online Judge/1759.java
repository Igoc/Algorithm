import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void guessPasswords(StringBuilder password, int vowelNumber, int consonantNumber, int startIndex,
                                      int l, int c, List<String> alphabets) throws IOException {
        if (password.length() == l) {
            if (vowelNumber >= 1 && consonantNumber >= 2) {
                out.write(password.toString() + '\n');
            }

            return;
        }

        for (int index = startIndex; index < c; index++) {
            String alphabet = alphabets.get(index);
            boolean vowel = alphabet.equals("a") || alphabet.equals("e") || alphabet.equals("i") || alphabet.equals("o") || alphabet.equals("u");

            password.append(alphabet);

            if (vowel) {
                guessPasswords(password, vowelNumber + 1, consonantNumber, index + 1, l, c, alphabets);
            } else {
                guessPasswords(password, vowelNumber, consonantNumber + 1, index + 1, l, c, alphabets);
            }

            password.deleteCharAt(password.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int L = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        List<String> alphabets = Arrays.stream(in.readLine().split(" "))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        guessPasswords(new StringBuilder(), 0, 0, 0, L, C, alphabets);

        in.close();
        out.flush();
        out.close();
    }
}