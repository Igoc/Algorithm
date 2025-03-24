import java.io.*;

public class Main {
    static final int MAXIMUM_SWITCH_PRESS_COUNT = 100000;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        boolean[] initialBulbs = convertStringToBooleans(in.readLine());
        boolean[] targetBulbs = convertStringToBooleans(in.readLine());

        int output = MAXIMUM_SWITCH_PRESS_COUNT + 1;

        output = Math.min(output, calculateMinimumSwitchPressCount(initialBulbs.clone(), targetBulbs));
        toggleSwitch(initialBulbs, 0);
        output = Math.min(output, calculateMinimumSwitchPressCount(initialBulbs.clone(), targetBulbs) + 1);

        out.write((output > MAXIMUM_SWITCH_PRESS_COUNT) ? ("-1") : (String.valueOf(output)));

        in.close();
        out.flush();
        out.close();
    }

    public static boolean[] convertStringToBooleans(String string) {
        boolean[] result = new boolean[string.length()];

        for (int index = 0; index < string.length(); index++) {
            result[index] = string.charAt(index) == '1';
        }

        return result;
    }

    public static int calculateMinimumSwitchPressCount(boolean[] initialBulbs, boolean[] targetBulbs) {
        int result = 0;

        for (int index = 1; index < initialBulbs.length; index++) {
            if (initialBulbs[index - 1] != targetBulbs[index - 1]) {
                toggleSwitch(initialBulbs, index);
                result++;
            }
        }

        if (initialBulbs[initialBulbs.length - 1] != targetBulbs[targetBulbs.length - 1]) {
            result = MAXIMUM_SWITCH_PRESS_COUNT + 1;
        }

        return result;
    }

    public static void toggleSwitch(boolean[] bulbs, int index) {
        if (index < 0 || index >= bulbs.length) {
            return;
        }

        if (index > 0) {
            bulbs[index - 1] = !bulbs[index - 1];
        }

        bulbs[index] = !bulbs[index];

        if (index < bulbs.length - 1) {
            bulbs[index + 1] = !bulbs[index + 1];
        }
    }
}