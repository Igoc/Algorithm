import java.io.*;
import java.util.*;

class Position {
    public long x;
    public long darkness;

    public Position(long x, long darkness) {
        this.x = x;
        this.darkness = darkness;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        long L = Long.parseLong(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        long[] A = Arrays.stream(in.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        Queue<Position> queue = new LinkedList<>();
        Set<Long> visits = new HashSet<>();
        int count = 0;

        for (long x : A) {
            queue.offer(new Position(x, 0L));
            visits.add(x);
        }

        while (count < K) {
            Position position = queue.poll();

            out.write(position.darkness + "\n");
            count++;

            if (position.x - 1 >= 0 && !visits.contains(position.x - 1)) {
                queue.offer(new Position(position.x - 1, position.darkness + 1));
                visits.add(position.x - 1);
            }

            if (position.x + 1 <= L && !visits.contains(position.x + 1)) {
                queue.offer(new Position(position.x + 1, position.darkness + 1));
                visits.add(position.x + 1);
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}