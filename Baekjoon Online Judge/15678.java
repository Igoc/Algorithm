import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public long maximumValue;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, long[] elements) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex == endIndex) {
            this.maximumValue = elements[startIndex];
        } else {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, elements);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, elements);
            this.maximumValue = Math.max(this.leftChild.maximumValue, this.rightChild.maximumValue);
        }
    }

    public long findMaximumValue(int startIndex, int endIndex) {
        if (endIndex < this.startIndex || this.endIndex < startIndex) {
            return Long.MIN_VALUE;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            return maximumValue;
        }

        return Math.max(leftChild.findMaximumValue(startIndex, endIndex), rightChild.findMaximumValue(startIndex, endIndex));
    }

    public long updateValue(int index, long value) {
        if (index < startIndex || endIndex < index) {
            return maximumValue;
        }

        if (startIndex == endIndex) {
            return maximumValue = value;
        }

        return maximumValue = Math.max(leftChild.updateValue(index, value), rightChild.updateValue(index, value));
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        long[] K = Arrays.stream(in.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        SegmentTree segmentTree = new SegmentTree(0, N - 1, K);
        long output = K[0];

        for (int index = 1; index < N; index++) {
            int startIndex = Math.max(0, index - D);
            int endIndex = index - 1;
            long maximumValue = segmentTree.findMaximumValue(startIndex, endIndex);

            if (maximumValue > 0L) {
                K[index] += maximumValue;
                segmentTree.updateValue(index, K[index]);
            }

            output = Math.max(output, K[index]);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}