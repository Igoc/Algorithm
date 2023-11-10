import java.io.*;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public long lazyValue;
    public long sum;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, long[] elements) {
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

    public long calculatePrefixSum(int startIndex, int endIndex) {
        reflectLazyValue();

        if (this.endIndex < startIndex || endIndex < this.startIndex) {
            return 0L;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            return sum;
        }

        return leftChild.calculatePrefixSum(startIndex, endIndex) + rightChild.calculatePrefixSum(startIndex, endIndex);
    }

    public long addValue(int startIndex, int endIndex, long value) {
        reflectLazyValue();

        if (this.endIndex < startIndex || endIndex < this.startIndex) {
            return sum;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            sum += value * (this.endIndex - this.startIndex + 1);

            if (this.startIndex != this.endIndex) {
                leftChild.lazyValue += value;
                rightChild.lazyValue += value;
            }

            return sum;
        }

        return sum = leftChild.addValue(startIndex, endIndex, value) + rightChild.addValue(startIndex, endIndex, value);
    }

    private void reflectLazyValue() {
        if (lazyValue != 0L) {
            sum += lazyValue * (endIndex - startIndex + 1);

            if (startIndex != endIndex) {
                leftChild.lazyValue += lazyValue;
                rightChild.lazyValue += lazyValue;
            }

            lazyValue = 0L;
        }
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
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken()) - 1;

            if (a == 1) {
                long d = Long.parseLong(tokenizer.nextToken());

                segmentTree.addValue(b, c, d);
            } else {
                out.write(segmentTree.calculatePrefixSum(b, c) + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}