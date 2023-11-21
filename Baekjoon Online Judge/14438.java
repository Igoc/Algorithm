import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public int minimumValue;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, int[] elements) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex == endIndex) {
            this.minimumValue = elements[startIndex];
        } else {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, elements);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, elements);
            this.minimumValue = Math.min(this.leftChild.minimumValue, this.rightChild.minimumValue);
        }
    }

    public int replace(int index, int value) {
        if (index < startIndex || endIndex < index) {
            return minimumValue;
        }

        if (startIndex == endIndex) {
            return minimumValue = value;
        }

        return minimumValue = Math.min(leftChild.replace(index, value), rightChild.replace(index, value));
    }

    public int findMinimumValue(int startIndex, int endIndex) {
        if (this.endIndex < startIndex || endIndex < this.startIndex) {
            return Integer.MAX_VALUE;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            return minimumValue;
        }

        return Math.min(leftChild.findMinimumValue(startIndex, endIndex), rightChild.findMinimumValue(startIndex, endIndex));
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int M = Integer.parseInt(in.readLine());

        SegmentTree segmentTree = new SegmentTree(0, N - 1, A);

        for (int index = 0; index < M; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int type = Integer.parseInt(tokenizer.nextToken());
            int i = Integer.parseInt(tokenizer.nextToken()) - 1;

            if (type == 1) {
                int v = Integer.parseInt(tokenizer.nextToken());

                segmentTree.replace(i, v);
            } else {
                int j = Integer.parseInt(tokenizer.nextToken()) - 1;

                out.write(segmentTree.findMinimumValue(i, j) + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}