class Solution {
    public int solution(int storey) {
        int answer = 0;

        for (int digit = 1; digit <= 9; digit++) {
            int digitNumber = getDigitNumber(storey, digit);

            if (digitNumber > 5 || digitNumber == 5 && getDigitNumber(storey, digit + 1) >= 5) {
                storey += (10 - digitNumber) * (int) Math.pow(10, digit - 1);
                answer += 10 - digitNumber;
            } else {
                answer += digitNumber;
            }
        }

        return answer;
    }

    public int getDigitNumber(int number, int digit) {
        return (int) (number / (long) Math.pow(10, digit - 1) - number / (long) Math.pow(10, digit) * 10);
    }
}