import java.util.Arrays;

class Number implements Comparable<Number> {
    public int number;
    public int divisorNumber;

    public Number(int number, int divisorNumber) {
        this.number = number;
        this.divisorNumber = divisorNumber;
    }

    @Override
    public int compareTo(Number o) {
        if (divisorNumber == o.divisorNumber) {
            return Integer.compare(number, o.number);
        }

        return Integer.compare(o.divisorNumber, divisorNumber);
    }
}

class Solution {
    public int[] solution(int e, int[] starts) {
        Number[] numbers = new Number[e + 1];

        for (int number = 0; number <= e; number++) {
            numbers[number] = new Number(number, 0);
        }

        for (int multiplicand = 1; multiplicand <= e; multiplicand++) {
            for (int multiplier = 1; multiplicand * multiplier <= e; multiplier++) {
                numbers[multiplicand * multiplier].divisorNumber++;
            }
        }

        Arrays.sort(numbers);

        int[] answer = new int[starts.length];

        for (int index = 0; index < starts.length; index++) {
            for (Number number : numbers) {
                if (number.number >= starts[index]) {
                    answer[index] = number.number;

                    break;
                }
            }
        }

        return answer;
    }
}