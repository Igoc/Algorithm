import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long calculateEthernetCableNumber(List<Integer> ethernetCableLength, long length) {
        long output = 0L;

        for (int x : ethernetCableLength) {
            output += x / length;
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int K = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        List<Integer> ethernetCableLength = new ArrayList<>();

        for (int index = 0; index < K; index++) {
            ethernetCableLength.add(Integer.parseInt(in.readLine()));
        }

        long minLength = 0L;
        long maxLength = Collections.max(ethernetCableLength) + 1L;

        while (minLength + 1 < maxLength) {
            long length = (minLength + maxLength) / 2;

            if (calculateEthernetCableNumber(ethernetCableLength, length) >= N) {
                minLength = length;
            } else {
                maxLength = length;
            }
        }

        out.write(String.valueOf(minLength));

        in.close();
        out.flush();
        out.close();
    }
}