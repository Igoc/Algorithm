import java.io.*;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public long sum;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, long[] values) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex == endIndex) {
            this.sum = values[startIndex];
        } else {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, values);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, values);
            this.sum = this.leftChild.sum + this.rightChild.sum;
        }
    }

    public long calculatePrefixSum(int startIndex, int endIndex) {
        if (startIndex > this.endIndex || endIndex < this.startIndex) {
            return 0L;
        }

        if (startIndex <= this.startIndex && endIndex >= this.endIndex) {
            return sum;
        }

        return leftChild.calculatePrefixSum(startIndex, endIndex) + rightChild.calculatePrefixSum(startIndex, endIndex);
    }

    public long update(int index, long value) {
        if (index < startIndex || index > endIndex) {
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
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        long[] numbers = new long[N];

        for (int index = 0; index < N; index++) {
            numbers[index] = Long.parseLong(in.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(0, N - 1, numbers);

        for (int index = 0; index < M + K; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            long c = Long.parseLong(tokenizer.nextToken());

            if (a == 1) {
                segmentTree.update(b - 1, c);
            } else {
                out.write(segmentTree.calculatePrefixSum(b - 1, (int) (c - 1)) + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}