import java.util.ArrayList;
import java.util.List;

public class InterestingDigits {
    public int[] digits(int base) {
        List<Integer> output = new ArrayList<>();

        for (int digit = 2; digit < base; digit++) {
            int multiplier = 2;
            boolean interesting = true;

            while (digit * multiplier < base * base * base) {
                int multiple = digit * multiplier;
                int divisor = 1;
                int sum = 0;

                while (multiple / divisor != 0) {
                    sum += (multiple % (divisor * base) - multiple % divisor) / divisor;
                    divisor *= base;
                }

                if (sum % digit != 0) {
                    interesting = false;

                    break;
                }

                multiplier++;
            }

            if (interesting) {
                output.add(digit);
            }
        }

        return output.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}