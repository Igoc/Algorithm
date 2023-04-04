import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            String number = in.readLine();

            if (number.equals("0")) {
                break;
            }

            boolean palindromeNumber = true;

            for (int index = 0; index < number.length() / 2; index++) {
                if (number.charAt(index) != number.charAt(number.length() - index - 1)) {
                    palindromeNumber = false;

                    break;
                }
            }

            out.write((palindromeNumber) ? ("yes\n") : ("no\n"));
        }

        in.close();
        out.flush();
        out.close();
    }
}