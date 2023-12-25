import java.util.Arrays;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playbackSecond = convertTimeToSecond(play_time);
        int advertisingSecond = convertTimeToSecond(adv_time);

        long[] playbackCounts = new long[playbackSecond + 1];

        for (String log : logs) {
            String[] separatedLogs = log.split("-");

            playbackCounts[convertTimeToSecond(separatedLogs[0])]++;
            playbackCounts[convertTimeToSecond(separatedLogs[1])]--;
        }

        for (int second = 1; second < playbackCounts.length; second++) {
            playbackCounts[second] += playbackCounts[second - 1];
        }

        for (int second = 1; second < playbackCounts.length; second++) {
            playbackCounts[second] += playbackCounts[second - 1];
        }

        long maximumPlaybackCount = playbackCounts[advertisingSecond - 1];
        int answer = 0;

        for (int second = 1; second <= playbackSecond - advertisingSecond; second++) {
            long playbackCount = playbackCounts[second + advertisingSecond - 1] - playbackCounts[second - 1];

            if (playbackCount > maximumPlaybackCount) {
                maximumPlaybackCount = playbackCount;
                answer = second;
            }
        }

        return convertSecondToTime(answer);
    }

    public int convertTimeToSecond(String time) {
        int[] separatedTimes = Arrays.stream(time.split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();

        return separatedTimes[0] * 3600 + separatedTimes[1] * 60 + separatedTimes[2];
    }

    public String convertSecondToTime(int second) {
        return String.format("%02d:%02d:%02d", second / 3600, second % 3600 / 60, second % 60);
    }
}