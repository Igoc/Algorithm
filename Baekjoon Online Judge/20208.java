import java.io.*;
import java.util.*;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int calculateDistance(Coord coord) {
        return Math.abs(x - coord.x) + Math.abs(y - coord.y);
    }
}

class State {
    public int hp;
    public int count;
    public Coord coord;
    public boolean[] visited;

    public State(int hp, int count, Coord coord, boolean[] visited) {
        this.hp = hp;
        this.count = count;
        this.coord = coord;
        this.visited = visited;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int H = Integer.parseInt(tokenizer.nextToken());

        Coord home = new Coord(0, 0);
        List<Coord> mintChocolateMilk = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            tokenizer = new StringTokenizer(in.readLine());

            for (int x = 0; x < N; x++) {
                int space = Integer.parseInt(tokenizer.nextToken());

                if (space == 1) {
                    home = new Coord(x, y);
                } else if (space == 2) {
                    mintChocolateMilk.add(new Coord(x, y));
                }
            }
        }

        Queue<State> queue = new ArrayDeque<>(List.of(new State(M, 0, home, new boolean[mintChocolateMilk.size()])));
        int output = 0;

        while (!queue.isEmpty()) {
            State state = queue.poll();

            if (state.coord.calculateDistance(home) <= state.hp) {
                output = Math.max(output, state.count);
            }

            for (int index = 0; index < mintChocolateMilk.size(); index++) {
                int distance = state.coord.calculateDistance(mintChocolateMilk.get(index));

                if (!state.visited[index] && distance <= state.hp) {
                    state.visited[index] = true;
                    queue.offer(new State(state.hp - distance + H, state.count + 1, mintChocolateMilk.get(index), state.visited.clone()));
                    state.visited[index] = false;
                }
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}