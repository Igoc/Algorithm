import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<Set<Integer>> nextSingers = new ArrayList<>();

        for (int singer = 0; singer < N; singer++) {
            nextSingers.add(new HashSet<>());
        }

        int[] previousSingerNumbers = new int[N];

        for (int pd = 0; pd < M; pd++) {
            int[] sequence = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .map(singer -> singer - 1)
                    .toArray();

            for (int index = 2; index < sequence.length; index++) {
                if (!nextSingers.get(sequence[index - 1]).contains(sequence[index])) {
                    nextSingers.get(sequence[index - 1]).add(sequence[index]);
                    previousSingerNumbers[sequence[index]]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int singerCount = 0;
        StringBuilder output = new StringBuilder();

        for (int singer = 0; singer < N; singer++) {
            if (previousSingerNumbers[singer] == 0) {
                previousSingerNumbers[singer] = -1;
                queue.offer(singer);
                singerCount++;
                output.append(singer + 1).append("\n");
            }
        }

        while (!queue.isEmpty()) {
            int singer = queue.poll();

            for (int nextSinger : nextSingers.get(singer)) {
                if (previousSingerNumbers[nextSinger] == -1) {
                    continue;
                }

                previousSingerNumbers[nextSinger]--;

                if (previousSingerNumbers[nextSinger] == 0) {
                    previousSingerNumbers[nextSinger] = -1;
                    queue.offer(nextSinger);
                    singerCount++;
                    output.append(nextSinger + 1).append("\n");
                }
            }
        }

        if (singerCount == N) {
            out.write(output.toString());
        } else {
            out.write("0");
        }

        in.close();
        out.flush();
        out.close();
    }
}