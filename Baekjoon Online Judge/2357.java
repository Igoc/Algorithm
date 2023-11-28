import java.io.*;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public int minimumValue;
    public int maximumValue;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, int[] elements) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex == endIndex) {
            this.minimumValue = elements[startIndex];
            this.maximumValue = elements[startIndex];
        } else {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, elements);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, elements);
            this.minimumValue = Math.min(this.leftChild.minimumValue, this.rightChild.minimumValue);
            this.maximumValue = Math.max(this.leftChild.maximumValue, this.rightChild.maximumValue);
        }
    }

    public int[] findMinimumValueAndMaximumValue(int startIndex, int endIndex) {
        if (this.endIndex < startIndex || endIndex < this.startIndex) {
            return new int[]{Integer.MAX_VALUE, 0};
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            return new int[]{minimumValue, maximumValue};
        }

        int[] leftChildValues = leftChild.findMinimumValueAndMaximumValue(startIndex, endIndex);
        int[] rightChildValues = rightChild.findMinimumValueAndMaximumValue(startIndex, endIndex);

        return new int[]{Math.min(leftChildValues[0], rightChildValues[0]), Math.max(leftChildValues[1], rightChildValues[1])};
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = new int[N];

        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(in.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(0, N - 1, numbers);

        for (int index = 0; index < M; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;

            int[] outputs = segmentTree.findMinimumValueAndMaximumValue(a, b);

            out.write(outputs[0] + " " + outputs[1] + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}