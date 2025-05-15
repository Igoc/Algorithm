import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        int[][] planets = new int[M][N];
        int output = 0;

        for (int leftSpace = 0; leftSpace < M; leftSpace++) {
            planets[leftSpace] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Integer> sortedPlanets = Arrays.stream(planets[leftSpace])
                    .boxed()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            for (int planet = 0; planet < N; planet++) {
                planets[leftSpace][planet] = Collections.binarySearch(sortedPlanets, planets[leftSpace][planet]);
            }

            for (int rightSpace = 0; rightSpace < leftSpace; rightSpace++) {
                if (Arrays.compare(planets[leftSpace], planets[rightSpace]) == 0) {
                    output++;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}