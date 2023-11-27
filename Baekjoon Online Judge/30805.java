import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Sequence {
    public List<Element> sequence = new ArrayList<>();
}

class Element {
    public int row;
    public int col;
    public int number;

    public Element(int row, int col, int number) {
        this.row = row;
        this.col = col;
        this.number = number;
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

        int[] B = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Sequence[][] commonSubsequences = new Sequence[N + 1][M + 1];

        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= M; col++) {
                commonSubsequences[row][col] = new Sequence();
            }
        }

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                commonSubsequences[row][col].sequence = findLexicographicallyLargestSequence(commonSubsequences[row - 1][col].sequence, commonSubsequences[row][col - 1].sequence);
                commonSubsequences[row][col].sequence = findLexicographicallyLargestSequence(commonSubsequences[row][col].sequence, commonSubsequences[row - 1][col - 1].sequence);

                if (A[row - 1] == B[col - 1]) {
                    List<Element> commonSubsequence = new ArrayList<>();

                    for (Element element : commonSubsequences[row][col].sequence) {
                        if (element.number < A[row - 1]) {
                            break;
                        }

                        if (element.row < row - 1 && element.col < col - 1) {
                            commonSubsequence.add(element);
                        }
                    }

                    commonSubsequence.add(new Element(row - 1, col - 1, A[row - 1]));
                    commonSubsequences[row][col].sequence = commonSubsequence;
                }
            }
        }

        out.write(commonSubsequences[N][M].sequence.size() + "\n");

        if (!commonSubsequences[N][M].sequence.isEmpty()) {
            for (Element element : commonSubsequences[N][M].sequence) {
                out.write(element.number + " ");
            }
        }

        in.close();
        out.flush();
        out.close();
    }

    public static List<Element> findLexicographicallyLargestSequence(List<Element> sequence1, List<Element> sequence2) {
        int minimumSize = Math.min(sequence1.size(), sequence2.size());

        for (int index = 0; index < minimumSize; index++) {
            if (sequence1.get(index).number > sequence2.get(index).number) {
                return new ArrayList<>(sequence1);
            } else if (sequence1.get(index).number < sequence2.get(index).number) {
                return new ArrayList<>(sequence2);
            }
        }

        if (sequence1.size() > sequence2.size()) {
            return new ArrayList<>(sequence1);
        }

        return new ArrayList<>(sequence2);
    }
}