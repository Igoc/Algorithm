import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] lectureMinutes = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maximumMinute = 0;
        int totalMinute = 0;

        for (int lectureMinute : lectureMinutes) {
            maximumMinute = Math.max(maximumMinute, lectureMinute);
            totalMinute += lectureMinute;
        }

        int minimumSize = maximumMinute;
        int maximumSize = totalMinute;

        while (minimumSize <= maximumSize) {
            int size = (minimumSize + maximumSize) / 2;
            int currentBluRaySize = 0;
            int bluRayNumber = 1;

            for (int lectureMinute : lectureMinutes) {
                if (currentBluRaySize + lectureMinute > size) {
                    currentBluRaySize = lectureMinute;
                    bluRayNumber++;
                } else if (currentBluRaySize + lectureMinute == size) {
                    currentBluRaySize = 0;
                    bluRayNumber++;
                } else {
                    currentBluRaySize += lectureMinute;
                }
            }

            if (currentBluRaySize == 0) {
                bluRayNumber--;
            }

            if (bluRayNumber > M) {
                minimumSize = size + 1;
            } else {
                maximumSize = size - 1;
            }
        }

        out.write(String.valueOf(minimumSize));

        in.close();
        out.flush();
        out.close();
    }
}