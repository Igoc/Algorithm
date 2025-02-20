import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String sound = in.readLine();

        out.write(determineSound(sound));

        in.close();
        out.flush();
        out.close();
    }

    public static String determineSound(String sound) {
        for (int index = 0; index < sound.length(); index++) {
            switch (sound.charAt(index)) {
                case '0':
                    if (index >= sound.length() - 1 || sound.charAt(index + 1) == '0') {
                        return "NOISE";
                    }

                    index++;
                    break;

                case '1':
                    if (index >= sound.length() - 3 || sound.charAt(index + 1) == '1' || sound.charAt(index + 2) == '1') {
                        return "NOISE";
                    }

                    while (index < sound.length() - 1 && sound.charAt(index + 1) == '0') {
                        index++;
                    }

                    while (index < sound.length() - 1 && sound.charAt(index + 1) == '1') {
                        index++;
                    }

                    if (sound.charAt(index) == '0') {
                        return "NOISE";
                    }

                    if (index >= 4 && sound.charAt(index - 1) == '1' && index <= sound.length() - 4 && sound.charAt(index + 1) == '0' && sound.charAt(index + 2) == '0') {
                        index--;
                    }

                    break;
            }
        }

        return "SUBMARINE";
    }
}