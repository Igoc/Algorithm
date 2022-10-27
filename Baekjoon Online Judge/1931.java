import java.io.*;
import java.util.*;

class Conference implements Comparable<Conference> {
    public int startTime;
    public int endTime;

    public Conference(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Conference o) {
        if (endTime == o.endTime) {
            return startTime - o.startTime;
        }

        return endTime - o.endTime;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        List<Conference> conferences = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            conferences.add(new Conference(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        conferences.sort(Comparator.naturalOrder());

        int lastConferenceEndTime = 0;
        int output = 0;

        for (int index = 0; index < N; index++) {
            Conference conference = conferences.get(index);

            if (conference.startTime >= lastConferenceEndTime) {
                lastConferenceEndTime = conference.endTime;
                output++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}