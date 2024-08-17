import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class Alphabet {
    public int letter;
    public long weight;

    public Alphabet(int letter) {
        this.letter = letter;
        this.weight = 0L;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Alphabet[] alphabets = new Alphabet[10];
        boolean[] firstLetter = new boolean[10];

        for (int letter = 0; letter < 10; letter++) {
            alphabets[letter] = new Alphabet(letter);
        }

        String[] numbers = new String[N];

        for (int index = 0; index < N; index++) {
            String number = in.readLine();

            numbers[index] = number;
            firstLetter[number.charAt(0) - 'A'] = true;

            for (int digit = 0; digit < number.length(); digit++) {
                int letter = number.charAt(number.length() - digit - 1) - 'A';
                long weight = (long) Math.pow(10, digit);

                alphabets[letter].weight += weight;
            }
        }

        Arrays.sort(alphabets, Comparator.comparingLong(o -> o.weight));

        for (int digit = 0; digit < 10; digit++) {
            if (!firstLetter[alphabets[digit].letter]) {
                alphabets[digit].weight = -1L;

                break;
            }
        }

        Arrays.sort(alphabets, Comparator.comparingLong(o -> o.weight));

        int[] digits = new int[10];

        for (int digit = 0; digit < 10; digit++) {
            digits[alphabets[digit].letter] = digit;
        }

        long output = 0L;

        for (String number : numbers) {
            StringBuilder convertedNumber = new StringBuilder();

            for (char letter : number.toCharArray()) {
                convertedNumber.append(digits[letter - 'A']);
            }

            output += Long.parseLong(convertedNumber.toString());
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}