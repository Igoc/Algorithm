import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] sizes = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int T = Integer.parseInt(tokenizer.nextToken());
        int P = Integer.parseInt(tokenizer.nextToken());

        int tShirtBundleNumber = 0;
        int penBundleNumber = 0;
        int penNumber = 0;

        for (int size : sizes) {
            tShirtBundleNumber += (int) Math.ceil((double) size / (double) T);
            penBundleNumber += size;
        }

        penNumber = penBundleNumber % P;
        penBundleNumber /= P;

        out.write(tShirtBundleNumber + "\n");
        out.write(penBundleNumber + " " + penNumber);

        in.close();
        out.flush();
        out.close();
    }
}