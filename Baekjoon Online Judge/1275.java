import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public long sum;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, int[] elements) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex == endIndex) {
            this.sum = elements[startIndex];
        } else {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, elements);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, elements);
            this.sum = this.leftChild.sum + this.rightChild.sum;
        }
    }

    public long calculateSum(int startIndex, int endIndex) {
        if (endIndex < this.startIndex || this.endIndex < startIndex) {
            return 0L;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            return sum;
        }

        return leftChild.calculateSum(startIndex, endIndex) + rightChild.calculateSum(startIndex, endIndex);
    }

    public long update(int index, int value) {
        if (index < startIndex || endIndex < index) {
            return sum;
        }

        if (startIndex == endIndex) {
            return sum = value;
        }

        return sum = leftChild.update(index, value) + rightChild.update(index, value);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        SegmentTree segmentTree = new SegmentTree(0, N - 1, numbers);

        for (int query = 0; query < Q; query++) {
            tokenizer = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken());

            if (x > y) {
                x ^= y;
                y ^= x;
                x ^= y;
            }

            out.write(segmentTree.calculateSum(x, y) + "\n");
            segmentTree.update(a, b);
        }

        in.close();
        out.flush();
        out.close();
    }
}