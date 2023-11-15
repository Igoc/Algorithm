import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class SegmentTree {
    public int startIndex;
    public int endIndex;
    public int sum;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, int blankNumber) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex != endIndex) {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, blankNumber);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, blankNumber);
            this.sum = this.leftChild.sum + this.rightChild.sum;
        } else if (startIndex >= blankNumber) {
            this.sum = 1;
        }
    }

    public int calculateSequence(int index) {
        if (index < startIndex) {
            return 0;
        }

        if (index >= endIndex) {
            return sum;
        }

        return leftChild.calculateSequence(index) + rightChild.calculateSequence(index);
    }

    public int flip(int index) {
        if (endIndex < index || index < startIndex) {
            return sum;
        }

        if (startIndex == endIndex) {
            return sum = (sum + 1) % 2;
        }

        return sum = leftChild.flip(index) + rightChild.flip(index);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int testCaseNumber = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < testCaseNumber; testCase++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());

            int[] movies = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .map(movie -> movie - 1)
                    .toArray();

            int[] movieIndexes = new int[n];

            Arrays.setAll(movieIndexes, index -> index + m);

            SegmentTree segmentTree = new SegmentTree(0, n + m - 1, m);
            int topIndex = m - 1;

            for (int movie : movies) {
                out.write((segmentTree.calculateSequence(movieIndexes[movie]) - 1) + " ");

                segmentTree.flip(movieIndexes[movie]);
                segmentTree.flip(topIndex);

                movieIndexes[movie] = topIndex--;
            }

            out.write("\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}