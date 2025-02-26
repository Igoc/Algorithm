import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int R = Integer.parseInt(tokenizer.nextToken());

        int[][] A = new int[N][M];

        for (int row = 0; row < N; row++) {
            A[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] output = new int[N][M];

        for (int layer = 0; layer < Math.min(N, M) / 2; layer++) {
            List<Integer> sequence = findSequence(A, layer);
            int rows = A.length - layer * 2;
            int cols = A[0].length - layer * 2;
            int index = 0;

            for (int col = 0; col < cols - 1; col++) {
                output[layer][col + layer] = sequence.get((index + R) % sequence.size());
                index++;
            }

            for (int row = 0; row < rows - 1; row++) {
                output[row + layer][cols + layer - 1] = sequence.get((index + R) % sequence.size());
                index++;
            }

            for (int col = cols - 1; col >= 1; col--) {
                output[rows + layer - 1][col + layer] = sequence.get((index + R) % sequence.size());
                index++;
            }

            for (int row = rows - 1; row >= 1; row--) {
                output[row + layer][layer] = sequence.get((index + R) % sequence.size());
                index++;
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                out.write(output[row][col] + " ");
            }

            out.write('\n');
        }

        in.close();
        out.flush();
        out.close();
    }

    public static List<Integer> findSequence(int[][] array, int layer) {
        List<Integer> sequence = new ArrayList<>();
        int rows = array.length - layer * 2;
        int cols = array[0].length - layer * 2;

        for (int col = 0; col < cols - 1; col++) {
            sequence.add(array[layer][col + layer]);
        }

        for (int row = 0; row < rows - 1; row++) {
            sequence.add(array[row + layer][cols + layer - 1]);
        }

        for (int col = cols - 1; col >= 1; col--) {
            sequence.add(array[rows + layer - 1][col + layer]);
        }

        for (int row = rows - 1; row >= 1; row--) {
            sequence.add(array[row + layer][layer]);
        }

        return sequence;
    }
}