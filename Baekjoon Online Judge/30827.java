import java.io.*;
import java.util.*;

class Meeting {
    public int start;
    public int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Meeting[] meetings = new Meeting[N];

        for (int index = 0; index < N; index++) {
            tokenizer = new StringTokenizer(in.readLine());

            int s = Integer.parseInt(tokenizer.nextToken());
            int e = Integer.parseInt(tokenizer.nextToken());

            meetings[index] = new Meeting(s, e);
        }

        Arrays.sort(meetings, Comparator.comparingInt(o -> o.end));

        List<Integer> conferenceRooms = new ArrayList<>(Collections.nCopies(K, 0));
        int output = 0;

        for (Meeting meeting : meetings) {
            for (int index = 0; index < K; index++) {
                if (meeting.start > conferenceRooms.get(index)) {
                    conferenceRooms.set(index, meeting.end);
                    output++;
                    break;
                }
            }

            conferenceRooms.sort(Comparator.reverseOrder());
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}