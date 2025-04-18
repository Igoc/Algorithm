import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int H = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());

        int[] blocks = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int output = 0;

        for (int index = 1; index < W - 1; index++) {
            int leftHeight = 0;

            for (int left = index - 1; left >= 0; left--) {
                leftHeight = Math.max(leftHeight, blocks[left]);
            }

            int rightHeight = 0;

            for (int right = index + 1; right < W; right++) {
                rightHeight = Math.max(rightHeight, blocks[right]);
            }

            int height = Math.min(leftHeight, rightHeight);

            if (height > blocks[index]) {
                output += height - blocks[index];
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}