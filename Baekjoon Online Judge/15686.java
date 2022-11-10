import java.io.*;
import java.util.*;

class Pos {
    public int x;
    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int calculateDistance(Pos pos) {
        return Math.abs(x - pos.x) + Math.abs(y - pos.y);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int calculateChickenDistance(int startIndex, int depth, int chickenDistance, int[][] distances, int closureNumber) {
        if (depth == closureNumber) {
            int currentChickenDistance = 0;

            for (int houseIndex = 0; houseIndex < distances.length; houseIndex++) {
                int minDistance = Integer.MAX_VALUE;

                for (int chickenRestaurantIndex = 0; chickenRestaurantIndex < distances[houseIndex].length; chickenRestaurantIndex++) {
                    if (distances[houseIndex][chickenRestaurantIndex] < minDistance) {
                        minDistance = distances[houseIndex][chickenRestaurantIndex];
                    }
                }

                currentChickenDistance += minDistance;
            }

            return Math.min(currentChickenDistance, chickenDistance);
        }

        for (int chickenRestaurantIndex = startIndex; chickenRestaurantIndex < distances[0].length; chickenRestaurantIndex++) {
            int[] tempDistances = new int[distances.length];

            for (int houseIndex = 0; houseIndex < distances.length; houseIndex++) {
                tempDistances[houseIndex] = distances[houseIndex][chickenRestaurantIndex];
                distances[houseIndex][chickenRestaurantIndex] = Integer.MAX_VALUE;
            }

            chickenDistance = calculateChickenDistance(chickenRestaurantIndex + 1, depth + 1, chickenDistance, distances, closureNumber);

            for (int houseIndex = 0; houseIndex < distances.length; houseIndex++) {
                distances[houseIndex][chickenRestaurantIndex] = tempDistances[houseIndex];
            }
        }

        return chickenDistance;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        List<Pos> houses = new ArrayList<>();
        List<Pos> chickenRestaurants = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            String[] map = in.readLine().split(" ");

            for (int col = 0; col < N; col++) {
                if (map[col].equals("1")) {
                    houses.add(new Pos(col, row));
                } else if (map[col].equals("2")) {
                    chickenRestaurants.add(new Pos(col, row));
                }
            }
        }

        int[][] distances = new int[houses.size()][chickenRestaurants.size()];

        for (int houseIndex = 0; houseIndex < houses.size(); houseIndex++) {
            for (int chickenRestaurantIndex = 0; chickenRestaurantIndex < chickenRestaurants.size(); chickenRestaurantIndex++) {
                Pos house = houses.get(houseIndex);
                Pos chickenRestaurant = chickenRestaurants.get(chickenRestaurantIndex);

                distances[houseIndex][chickenRestaurantIndex] = house.calculateDistance(chickenRestaurant);
            }
        }

        int chickenDistance = calculateChickenDistance(0, 0, Integer.MAX_VALUE, distances, chickenRestaurants.size() - M);

        out.write(String.valueOf(chickenDistance));

        in.close();
        out.flush();
        out.close();
    }
}