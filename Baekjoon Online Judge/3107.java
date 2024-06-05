import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String ip = in.readLine();

        if (ip.charAt(0) == ':' && ip.charAt(1) == ':') {
            ip = '0' + ip;
        }

        if (ip.charAt(ip.length() - 2) == ':' && ip.charAt(ip.length() - 1) == ':') {
            ip += '0';
        }

        String[] groups = ip.split(":");

        for (int group = 0; group < groups.length; group++) {
            if (groups[group].isEmpty()) {
                for (int iteration = 0; iteration <= 8 - groups.length; iteration++) {
                    if (group + iteration > 0) {
                        out.write(':');
                    }

                    out.write("0000");
                }
            } else {
                if (group > 0) {
                    out.write(':');
                }

                out.write("0".repeat(4 - groups[group].length()) + groups[group]);
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}