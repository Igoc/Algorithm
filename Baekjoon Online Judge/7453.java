import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pointer {
    public int sum;
    public long count;

    public Pointer(int sum, long count) {
        this.sum = sum;
        this.count = count;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(in.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for (int index = 0; index < n; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            A[index] = Integer.parseInt(tokenizer.nextToken());
            B[index] = Integer.parseInt(tokenizer.nextToken());
            C[index] = Integer.parseInt(tokenizer.nextToken());
            D[index] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] sumAB = new int[n * n];
        int[] sumCD = new int[n * n];

        for (int source = 0; source < n; source++) {
            for (int destination = 0; destination < n; destination++) {
                sumAB[source * n + destination] = A[source] + B[destination];
                sumCD[source * n + destination] = C[source] + D[destination];
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        int indexAB = 0;
        int indexCD = sumCD.length - 1;
        long output = 0L;

        while (indexAB < sumAB.length && indexCD >= 0) {
            Pointer pointerAB = new Pointer(sumAB[indexAB], 0L);

            while (indexAB < sumAB.length && sumAB[indexAB] == pointerAB.sum) {
                indexAB++;
                pointerAB.count++;
            }

            while (indexCD >= 0) {
                Pointer pointerCD = new Pointer(sumCD[indexCD], 0L);

                if (pointerAB.sum + pointerCD.sum < 0) {
                    break;
                }

                while (indexCD >= 0 && sumCD[indexCD] == pointerCD.sum) {
                    indexCD--;
                    pointerCD.count++;
                }

                if (pointerAB.sum + pointerCD.sum == 0) {
                    output += pointerAB.count * pointerCD.count;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}