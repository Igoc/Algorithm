import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        boolean[][] words = new boolean[N][26];

        for (int index = 0; index < N; index++) {
            String word = in.readLine();

            for (char letter : word.toCharArray()) {
                words[index][letter - 'a'] = true;
            }
        }

        out.write(String.valueOf(calculateReadableWordNumber(0, new boolean[26], 0, K, words, 0)));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateReadableWordNumber(int currentLetter, boolean[] letters, int letterNumber, int maximumLetterNumber, boolean[][] words, int readableWordNumber) {
        if (letterNumber < maximumLetterNumber && currentLetter >= letters.length) {
            return readableWordNumber;
        }

        if (letterNumber >= maximumLetterNumber) {
            int currentReadableWordNumber = 0;

            loop:
            for (boolean[] word : words) {
                for (int letter = 0; letter < letters.length; letter++) {
                    if (word[letter] && !letters[letter]) {
                        continue loop;
                    }
                }

                currentReadableWordNumber++;
            }

            return Math.max(currentReadableWordNumber, readableWordNumber);
        }

        letters[currentLetter] = true;
        readableWordNumber = calculateReadableWordNumber(currentLetter + 1, letters, letterNumber + 1, maximumLetterNumber, words, readableWordNumber);

        letters[currentLetter] = false;
        readableWordNumber = calculateReadableWordNumber(currentLetter + 1, letters, letterNumber, maximumLetterNumber, words, readableWordNumber);

        return readableWordNumber;
    }
}