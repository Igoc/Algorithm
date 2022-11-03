import java.io.*;
import java.util.*;

class City implements Comparable<City> {
    public int remainingDistance;
    public int oilPrice;

    public City(int remainingDistance, int oilPrice) {
        this.remainingDistance = remainingDistance;
        this.oilPrice = oilPrice;
    }

    @Override
    public int compareTo(City o) {
        return oilPrice - o.oilPrice;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        StringTokenizer roadLengthTokenizer = new StringTokenizer(in.readLine());
        StringTokenizer oilPriceTokenizer = new StringTokenizer(in.readLine());

        List<City> cities = new ArrayList<>();
        int totalDistance = 0;

        for (int index = 0; index < N; index++) {
            int roadLength = (index == 0) ? (0) : (Integer.parseInt(roadLengthTokenizer.nextToken()));
            int oilPrice = Integer.parseInt(oilPriceTokenizer.nextToken());

            cities.add(new City(roadLength, oilPrice));
            totalDistance += roadLength;
        }

        int remainingDistance = totalDistance;

        for (int index = 0; index < N; index++) {
            remainingDistance -= cities.get(index).remainingDistance;
            cities.get(index).remainingDistance = remainingDistance;
        }

        cities.sort(Comparator.naturalOrder());

        int moveDistance = 0;
        long output = 0L;

        for (int index = 0; index < N; index++) {
            City city = cities.get(index);

            if (city.remainingDistance > moveDistance) {
                int distance = city.remainingDistance - moveDistance;

                output += (long) (city.oilPrice) * (long) (distance);
                moveDistance += distance;
            }

            if (moveDistance >= totalDistance) {
                break;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}