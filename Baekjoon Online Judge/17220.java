import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> nextSuppliers = new ArrayList<>();
        int[] remainingSupplierNumbers = new int[N];

        for (int supplier = 0; supplier < N; supplier++) {
            nextSuppliers.add(new ArrayList<>());
        }

        for (int index = 0; index < M; index++) {
            String[] relationship = in.readLine().split(" ");

            nextSuppliers.get(relationship[0].charAt(0) - 'A').add(relationship[1].charAt(0) - 'A');
            remainingSupplierNumbers[relationship[1].charAt(0) - 'A']++;
        }

        for (int supplier = 0; supplier < N; supplier++) {
            if (remainingSupplierNumbers[supplier] == 0) {
                remainingSupplierNumbers[supplier] = -1;
            }
        }

        tokenizer = new StringTokenizer(in.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        int identifiedSupplierNumber = Integer.parseInt(tokenizer.nextToken());

        for (int index = 0; index < identifiedSupplierNumber; index++) {
            int identifiedSupplier = tokenizer.nextToken().charAt(0) - 'A';

            remainingSupplierNumbers[identifiedSupplier] = -1;

            for (int nextSupplier : nextSuppliers.get(identifiedSupplier)) {
                remainingSupplierNumbers[nextSupplier]--;

                if (remainingSupplierNumbers[nextSupplier] == 0) {
                    deque.offer(nextSupplier);
                    remainingSupplierNumbers[nextSupplier]--;
                }
            }
        }

        while (!deque.isEmpty()) {
            int supplier = deque.poll();

            for (int nextSupplier : nextSuppliers.get(supplier)) {
                remainingSupplierNumbers[nextSupplier]--;

                if (remainingSupplierNumbers[nextSupplier] == 0) {
                    deque.offer(nextSupplier);
                    remainingSupplierNumbers[nextSupplier]--;
                }
            }
        }

        int output = 0;

        for (int supplier = 0; supplier < N; supplier++) {
            if (remainingSupplierNumbers[supplier] > 0) {
                output++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}