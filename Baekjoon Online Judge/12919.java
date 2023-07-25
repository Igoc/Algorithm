import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int determineChangePossibility(String source, StringBuilder destination) {
        if (source.length() >= destination.length()) {
            return (source.equals(destination.toString())) ? (1) : (0);
        }

        int output = 0;

        if (destination.charAt(destination.length() - 1) == 'A') {
            destination.deleteCharAt(destination.length() - 1);
            output = Math.max(output, determineChangePossibility(source, destination));
            destination.append('A');
        }

        if (destination.charAt(0) == 'B') {
            destination.reverse().deleteCharAt(destination.length() - 1);
            output = Math.max(output, determineChangePossibility(source, destination));
            destination.append('B').reverse();
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        String S = in.readLine();
        String T = in.readLine();

        out.write(String.valueOf(determineChangePossibility(S, new StringBuilder(T))));

        in.close();
        out.flush();
        out.close();
    }
}