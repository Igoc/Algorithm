import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String word1 = in.readLine();
        String word2 = in.readLine();

        int[] alphabetCount1 = new int[26];
        int[] alphabetCount2 = new int[26];

        for (int index = 0; index < word1.length(); index++) {
            alphabetCount1[word1.charAt(index) - 'a']++;
        }

        for (int index = 0; index < word2.length(); index++) {
            alphabetCount2[word2.charAt(index) - 'a']++;
        }

        int output = 0;

        for (int index = 0; index < 26; index++) {
            output += Math.abs(alphabetCount1[index] - alphabetCount2[index]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}