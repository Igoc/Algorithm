import java.io.*;
import java.util.*;

class AlphabetWeight implements Comparable<AlphabetWeight> {
    public char alphabet;
    public int weight;

    public AlphabetWeight(char alphabet, int weight) {
        this.alphabet = alphabet;
        this.weight = weight;
    }

    public void addWeight(int weight) {
        this.weight += weight;
    }

    @Override
    public int compareTo(AlphabetWeight o) {
        return weight - o.weight;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<String> words = new ArrayList<>();
        List<AlphabetWeight> alphabetWeights = new ArrayList<>();

        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            alphabetWeights.add(new AlphabetWeight(alphabet, 0));
        }

        for (int wordIndex = 0; wordIndex < N; wordIndex++) {
            String word = in.readLine();
            int weight = 1;

            words.add(word);

            for (int alphabetIndex = word.length() - 1; alphabetIndex >= 0; alphabetIndex--) {
                alphabetWeights.get(word.charAt(alphabetIndex) - 'A').addWeight(weight);
                weight *= 10;
            }
        }

        alphabetWeights.sort(Comparator.reverseOrder());

        int[] alphabetNumbers = new int[26];

        for (int index = 0; index < 10; index++) {
            alphabetNumbers[alphabetWeights.get(index).alphabet - 'A'] = 9 - index;
        }

        int output = 0;

        for (int wordIndex = 0; wordIndex < N; wordIndex++) {
            String word = words.get(wordIndex);
            int wordNumber = 0;
            int offset = 1;

            for (int alphabetIndex = word.length() - 1; alphabetIndex >= 0; alphabetIndex--) {
                wordNumber += alphabetNumbers[word.charAt(alphabetIndex) - 'A'] * offset;
                offset *= 10;
            }

            output += wordNumber;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}