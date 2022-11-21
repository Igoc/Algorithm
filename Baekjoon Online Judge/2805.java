import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static long calculateTreeLength(List<Integer> treeHeights, int h) {
        long output = 0L;

        for (int treeHeight : treeHeights) {
            if (treeHeight > h) {
                output += treeHeight - h;
            }
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<Integer> treeHeights = Arrays.stream(in.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int minHeight = 0;
        int maxHeight = Collections.max(treeHeights);

        while (minHeight + 1 < maxHeight) {
            int H = (minHeight + maxHeight) / 2;

            if (calculateTreeLength(treeHeights, H) >= M) {
                minHeight = H;
            } else {
                maxHeight = H;
            }
        }

        out.write(String.valueOf(minHeight));

        in.close();
        out.flush();
        out.close();
    }
}