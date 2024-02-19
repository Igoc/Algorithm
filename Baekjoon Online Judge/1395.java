import java.io.*;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public int count;
    public boolean lazyToggle;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex != endIndex) {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex);
        }
    }

    public int count(int startIndex, int endIndex) {
        reflectLazyToggle();

        if (endIndex < this.startIndex || this.endIndex < startIndex) {
            return 0;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            return count;
        }

        return leftChild.count(startIndex, endIndex) + rightChild.count(startIndex, endIndex);
    }

    public int toggle(int startIndex, int endIndex) {
        reflectLazyToggle();

        if (endIndex < this.startIndex || this.endIndex < startIndex) {
            return count;
        }

        if (startIndex <= this.startIndex && this.endIndex <= endIndex) {
            if (this.startIndex != this.endIndex) {
                leftChild.lazyToggle = !leftChild.lazyToggle;
                rightChild.lazyToggle = !rightChild.lazyToggle;
            }

            return count = this.endIndex - this.startIndex + 1 - count;
        }

        return count = leftChild.toggle(startIndex, endIndex) + rightChild.toggle(startIndex, endIndex);
    }

    public void reflectLazyToggle() {
        if (lazyToggle) {
            count = endIndex - startIndex + 1 - count;

            if (startIndex != endIndex) {
                leftChild.lazyToggle = !leftChild.lazyToggle;
                rightChild.lazyToggle = !rightChild.lazyToggle;
            }

            lazyToggle = false;
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

        SegmentTree segmentTree = new SegmentTree(0, N - 1);

        for (int query = 0; query < M; query++) {
            tokenizer = new StringTokenizer(in.readLine());

            int O = Integer.parseInt(tokenizer.nextToken());
            int S = Integer.parseInt(tokenizer.nextToken()) - 1;
            int T = Integer.parseInt(tokenizer.nextToken()) - 1;

            if (O == 0) {
                segmentTree.toggle(S, T);
            } else {
                out.write(segmentTree.count(S, T) + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}