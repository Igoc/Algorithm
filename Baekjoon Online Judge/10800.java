import java.io.*;
import java.util.*;

class Ball implements Comparable<Ball> {
    public int index;
    public int color;
    public int size;

    public Ball(int index, int color, int size) {
        this.index = index;
        this.color = color;
        this.size = size;
    }

    @Override
    public int compareTo(Ball o) {
        return Integer.compare(size, o.size);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Ball[] balls = new Ball[N];
        Map<Integer, List<Integer>> colors = new HashMap<>();
        int[] sizes = new int[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            int C = Integer.parseInt(tokenizer.nextToken());
            int S = Integer.parseInt(tokenizer.nextToken());

            balls[index] = new Ball(index, C, S);
            sizes[index] = S;

            if (!colors.containsKey(C)) {
                colors.put(C, new ArrayList<>());
            }

            colors.get(C).add(S);
        }

        Arrays.sort(balls);
        Arrays.sort(sizes);

        int sizeIndex = 0;
        Map<Integer, Integer> colorSizeIndexes = new HashMap<>();

        colors.keySet().forEach(color -> {
            List<Integer> colorSizes = colors.get(color);

            colorSizes.sort(Comparator.naturalOrder());
            colorSizeIndexes.put(color, 0);
        });

        int[] outputs = new int[N];

        for (int index = 0; index < N; index++) {
            int color = balls[index].color;
            int size = balls[index].size;

            while (sizes[sizeIndex] < size) {
                if (sizeIndex > 0) {
                    sizes[sizeIndex] += sizes[sizeIndex - 1];
                }

                sizeIndex++;
            }

            List<Integer> colorSizes = colors.get(color);

            while (colorSizes.get(colorSizeIndexes.get(color)) < size) {
                int colorSizeIndex = colorSizeIndexes.get(color);

                if (colorSizeIndex > 0) {
                    colorSizes.set(
                            colorSizeIndex,
                            colorSizes.get(colorSizeIndex) + colorSizes.get(colorSizeIndex - 1)
                    );
                }

                colorSizeIndexes.replace(color, colorSizeIndex + 1);
            }

            if (sizeIndex > 0) {
                outputs[balls[index].index] = sizes[sizeIndex - 1];

                if (colorSizeIndexes.get(color) > 0) {
                    outputs[balls[index].index] -= colorSizes.get(colorSizeIndexes.get(color) - 1);
                }
            }
        }

        for (int output : outputs) {
            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}