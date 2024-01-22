import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        int n = Integer.parseInt(in.readLine());

        int[] A = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int m = Integer.parseInt(in.readLine());

        int[] B = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> aPrefixSums = calculatePrefixSums(A);
        List<Integer> bPrefixSums = calculatePrefixSums(B);

        aPrefixSums.sort(Comparator.naturalOrder());
        bPrefixSums.sort(Comparator.naturalOrder());

        int bRightIndex = bPrefixSums.size() - 1;
        long output = 0L;

        for (int aLeftIndex = 0; aLeftIndex < aPrefixSums.size(); aLeftIndex++) {
            int aPrefixSum = aPrefixSums.get(aLeftIndex);
            int aRightIndex = aLeftIndex;

            while (aRightIndex < aPrefixSums.size() && aPrefixSums.get(aRightIndex) == aPrefixSum) {
                aRightIndex++;
            }

            while (bRightIndex >= 0 && aPrefixSum + bPrefixSums.get(bRightIndex) > T) {
                bRightIndex--;
            }

            if (bRightIndex >= 0 && aPrefixSum + bPrefixSums.get(bRightIndex) == T) {
                int bPrefixSum = bPrefixSums.get(bRightIndex);
                int bLeftIndex = bRightIndex;

                while (bLeftIndex >= 0 && bPrefixSums.get(bLeftIndex) == bPrefixSum) {
                    bLeftIndex--;
                }

                output += (long) (aRightIndex - aLeftIndex) * (long) (bRightIndex - bLeftIndex);
                bRightIndex = bLeftIndex;
            }

            aLeftIndex = aRightIndex - 1;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static List<Integer> calculatePrefixSums(int[] elements) {
        int[] cumulativeHistogram = elements.clone();

        for (int index = 1; index < cumulativeHistogram.length; index++) {
            cumulativeHistogram[index] += cumulativeHistogram[index - 1];
        }

        List<Integer> prefixSums = new ArrayList<>();

        for (int rightIndex = 0; rightIndex < cumulativeHistogram.length; rightIndex++) {
            for (int leftIndex = 0; leftIndex <= rightIndex; leftIndex++) {
                int sum = cumulativeHistogram[rightIndex];

                if (leftIndex > 0) {
                    sum -= cumulativeHistogram[leftIndex - 1];
                }

                prefixSums.add(sum);
            }
        }

        return prefixSums;
    }
}