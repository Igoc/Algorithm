import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] inputs = {in.readLine(), in.readLine(), in.readLine()};

        for (int index = 0; index < 3; index++) {
            if (!inputs[index].equals("FizzBuzz") && !inputs[index].equals("Fizz") && !inputs[index].equals("Buzz")) {
                int number = Integer.parseInt(inputs[index]) - index + 3;

                if (number % 3 == 0 && number % 5 == 0) {
                    out.write("FizzBuzz");
                } else if (number % 3 == 0) {
                    out.write("Fizz");
                } else if (number % 5 == 0) {
                    out.write("Buzz");
                } else {
                    out.write(String.valueOf(number));
                }

                break;
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}