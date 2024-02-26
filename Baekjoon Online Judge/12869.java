import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        int[] healthPoints = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maximumTotalHealthPoint = convertHealthPointsToTotalHealthPoint(healthPoints);

        int[] attackCounts = new int[maximumTotalHealthPoint + 1];

        Arrays.fill(attackCounts, Integer.MAX_VALUE);
        attackCounts[maximumTotalHealthPoint] = 0;

        for (int totalHealthPoint = maximumTotalHealthPoint; totalHealthPoint >= 0; totalHealthPoint--) {
            if (attackCounts[totalHealthPoint] == Integer.MAX_VALUE) {
                continue;
            }

            int[] currentHealthPoints = convertTotalHealthPointToHealthPoints(totalHealthPoint, N);

            int[][] nextHealthPoints = {
                    attack(currentHealthPoints, new int[]{1, 3, 9}),
                    attack(currentHealthPoints, new int[]{1, 9, 3}),
                    attack(currentHealthPoints, new int[]{3, 1, 9}),
                    attack(currentHealthPoints, new int[]{3, 9, 1}),
                    attack(currentHealthPoints, new int[]{9, 1, 3}),
                    attack(currentHealthPoints, new int[]{9, 3, 1})
            };

            for (int[] nextHealthPoint : nextHealthPoints) {
                int nextTotalHealthPoint = convertHealthPointsToTotalHealthPoint(nextHealthPoint);

                attackCounts[nextTotalHealthPoint] = Math.min(attackCounts[nextTotalHealthPoint], attackCounts[totalHealthPoint] + 1);
            }
        }

        out.write(String.valueOf(attackCounts[0]));

        in.close();
        out.flush();
        out.close();
    }

    public static int[] attack(int[] healthPoints, int[] damage) {
        int[] outputs = new int[healthPoints.length];

        for (int index = 0; index < healthPoints.length; index++) {
            outputs[index] = Math.max(0, healthPoints[index] - damage[index]);
        }

        return outputs;
    }

    public static int convertHealthPointsToTotalHealthPoint(int[] healthPoints) {
        int totalHealthPoint = 0;

        for (int index = 0; index < healthPoints.length; index++) {
            totalHealthPoint += healthPoints[index] << (index * 6);
        }

        return totalHealthPoint;
    }

    public static int[] convertTotalHealthPointToHealthPoints(int totalHealthPoint, int size) {
        int[] healthPoints = new int[size];

        for (int index = 0; index < size; index++) {
            healthPoints[index] = (totalHealthPoint >> (index * 6)) & 0b111111;
        }

        return healthPoints;
    }
}