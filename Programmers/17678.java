import java.util.Arrays;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] minuteTable = Arrays.stream(timetable)
                .map(this::convertTimeToMinute)
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();

        int crew = 0;
        int currentMinute = 540;
        int maximumMinute = 0;

        for (int shuttle = 0; shuttle < n; shuttle++) {
            int passengerNumber = 0;

            while (crew < minuteTable.length && minuteTable[crew] <= currentMinute && passengerNumber < m) {
                crew++;
                passengerNumber++;
            }

            if (passengerNumber < m) {
                maximumMinute = currentMinute;

                if (shuttle < n - 1) {
                    maximumMinute += t - 1;
                }
            } else {
                maximumMinute = minuteTable[crew - 1] - 1;
            }

            currentMinute += t;
        }

        return convertMinuteToTime(maximumMinute);
    }

    public int convertTimeToMinute(String time) {
        int[] separatedTimes = Arrays.stream(time.split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();

        return separatedTimes[0] * 60 + separatedTimes[1];
    }

    public String convertMinuteToTime(int minute) {
        return String.format("%02d:%02d", minute / 60, minute % 60);
    }
}