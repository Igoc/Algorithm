import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[] inOrder = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] postOrder = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] inOrderIndexes = new int[n + 1];

        for (int index = 0; index < n; index++) {
            inOrderIndexes[inOrder[index]] = index;
        }

        traverseTreeWithPreOrder(inOrderIndexes, 0, postOrder, 0, n - 1);

        in.close();
        out.flush();
        out.close();
    }

    public static void traverseTreeWithPreOrder(int[] inOrderIndexes, int inOrderStartIndex, int[] postOrder, int postOrderStartIndex, int postOrderEndIndex) throws IOException {
        int root = postOrder[postOrderEndIndex];

        out.write(root + " ");

        if (postOrderStartIndex <= postOrderStartIndex + inOrderIndexes[root] - inOrderStartIndex - 1) {
            traverseTreeWithPreOrder(inOrderIndexes, inOrderStartIndex, postOrder, postOrderStartIndex, postOrderStartIndex + inOrderIndexes[root] - inOrderStartIndex - 1);
        }

        if (postOrderStartIndex + inOrderIndexes[root] - inOrderStartIndex <= postOrderEndIndex - 1) {
            traverseTreeWithPreOrder(inOrderIndexes, inOrderIndexes[root] + 1, postOrder, postOrderStartIndex + inOrderIndexes[root] - inOrderStartIndex, postOrderEndIndex - 1);
        }
    }
}