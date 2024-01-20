import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] instructions = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] expendedPower = new int[37];

        Arrays.fill(expendedPower, Integer.MAX_VALUE);
        expendedPower[0] = 0;

        for (int instruction : instructions) {
            if (instruction == 0) {
                break;
            }

            int[] nextExpendedPower = new int[expendedPower.length];

            Arrays.fill(nextExpendedPower, Integer.MAX_VALUE);

            for (int feet = 0; feet < expendedPower.length; feet++) {
                if (expendedPower[feet] == Integer.MAX_VALUE) {
                    continue;
                }

                int leftFoot = feet >> 3;
                int rightFoot = feet & 7;

                if (leftFoot == instruction || rightFoot == instruction) {
                    nextExpendedPower[feet] = Math.min(nextExpendedPower[feet], expendedPower[feet] + 1);
                } else {
                    nextExpendedPower[instruction << 3 | rightFoot] = Math.min(
                            nextExpendedPower[instruction << 3 | rightFoot],
                            expendedPower[feet] + ((leftFoot == 0) ? (2) : (4 - Math.abs(instruction - leftFoot) % 2))
                    );

                    nextExpendedPower[leftFoot << 3 | instruction] = Math.min(
                            nextExpendedPower[leftFoot << 3 | instruction],
                            expendedPower[feet] + ((rightFoot == 0) ? (2) : (4 - Math.abs(instruction - rightFoot) % 2))
                    );
                }
            }

            expendedPower = nextExpendedPower;
        }

        int output = Integer.MAX_VALUE;

        for (int power : expendedPower) {
            output = Math.min(output, power);
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}