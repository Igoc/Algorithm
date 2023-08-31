import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Music implements Comparable<Music> {
    public int index;
    public String title;
    public int playingTime;

    public Music(int index, String title, int playingTime) {
        this.index = index;
        this.title = title;
        this.playingTime = playingTime;
    }

    @Override
    public int compareTo(Music o) {
        if (playingTime == o.playingTime) {
            return Integer.compare(index, o.index);
        }

        return Integer.compare(o.playingTime, playingTime);
    }
}

class Solution {
    public String solution(String m, String[] musicinfos) {
        List<String> memorizedMelody = new ArrayList<>();

        for (char note : m.toCharArray()) {
            if (note != '#') {
                memorizedMelody.add(String.valueOf(note));
            } else {
                memorizedMelody.set(memorizedMelody.size() - 1, memorizedMelody.get(memorizedMelody.size() - 1) + '#');
            }
        }

        List<Music> musics = new ArrayList<>();

        for (int index = 0; index < musicinfos.length; index++) {
            String[] musicinfo = musicinfos[index].split(",");

            int[] startTime = Arrays.stream(musicinfo[0].split(":"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] endTime = Arrays.stream(musicinfo[1].split(":"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int playingTime = (endTime[0] - startTime[0]) * 60 + endTime[1] - startTime[1];

            String title = musicinfo[2];

            List<String> melody = new ArrayList<>();

            for (char note : musicinfo[3].toCharArray()) {
                if (note != '#') {
                    melody.add(String.valueOf(note));
                } else {
                    melody.set(melody.size() - 1, melody.get(melody.size() - 1) + '#');
                }
            }

            int sequence = 0;

            for (int time = 0; time < playingTime; time++) {
                if (!melody.get(time % melody.size()).equals(memorizedMelody.get(sequence))) {
                    sequence = 0;
                }

                if (melody.get(time % melody.size()).equals(memorizedMelody.get(sequence))) {
                    sequence++;
                }

                if (sequence == memorizedMelody.size()) {
                    musics.add(new Music(index, title, playingTime));

                    break;
                }
            }
        }

        musics.sort(Comparator.naturalOrder());

        if (musics.size() == 0) {
            return "(None)";
        }

        return musics.get(0).title;
    }
}