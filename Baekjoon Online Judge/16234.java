import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Federation {
    public int population;
    public int countryNumber;

    public Federation() {
        this.population = 0;
        this.countryNumber = 0;
    }

    public void addCountry(int population) {
        this.population += population;
        this.countryNumber++;
    }
}

public class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());
        int R = Integer.parseInt(tokenizer.nextToken());

        int[][] A = new int[N][N];

        for (int row = 0; row < N; row++) {
            A[row] = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int output = 0;

        while (true) {
            int[] parents = new int[N * N];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    parents[row * N + col] = row * N + col;
                }
            }

            boolean finished = true;

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    for (int dir = 0; dir < 4; dir++) {
                        int x = col + DX[dir];
                        int y = row + DY[dir];

                        if (x >= 0 && x < N && y >= 0 && y < N) {
                            int difference = Math.abs(A[row][col] - A[y][x]);

                            if (L <= difference && difference <= R) {
                                union(parents, row * N + col, y * N + x);
                                finished = false;
                            }
                        }
                    }
                }
            }

            if (finished) {
                break;
            }

            Map<Integer, Federation> federations = new HashMap<>();

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    int parent = find(parents, row * N + col);

                    federations.putIfAbsent(parent, new Federation());
                    federations.get(parent).addCountry(A[row][col]);
                }
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    Federation federation = federations.get(parents[row * N + col]);

                    A[row][col] = federation.population / federation.countryNumber;
                }
            }

            output++;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static void union(int[] parents, int x, int y) {
        x = find(parents, x);
        y = find(parents, y);

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }

    public static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents, parents[x]);
    }
}