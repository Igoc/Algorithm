import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String expression = in.readLine();

        StringBuilder number = new StringBuilder();
        boolean minus = false;
        int output = 0;

        for (int index = 0; index < expression.length(); index++) {
            char value = expression.charAt(index);

            if (value != '+' && value != '-') {
                number.append(value);
            } else {
                if (minus) {
                    output -= Integer.parseInt(number.toString());
                } else {
                    output += Integer.parseInt(number.toString());

                    if (value == '-') {
                        minus = true;
                    }
                }

                number = new StringBuilder();
            }
        }

        if (minus) {
            output -= Integer.parseInt(number.toString());
        } else {
            output += Integer.parseInt(number.toString());
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}