import java.io.*;
import java.util.*;

class Building {
    public int time;
    public int prerequisiteTime;
    public int remainingBuildingNumber;
    public List<Building> nextBuildings;

    public Building() {
        this.time = 0;
        this.prerequisiteTime = 0;
        this.remainingBuildingNumber = 0;
        this.nextBuildings = new ArrayList<>();
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Building[] buildings = new Building[N];

        for (int index = 0; index < N; index++) {
            buildings[index] = new Building();
        }

        for (int index = 0; index < N; index++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());

            buildings[index].time = Integer.parseInt(tokenizer.nextToken());

            while (tokenizer.hasMoreTokens()) {
                int prerequisiteIndex = Integer.parseInt(tokenizer.nextToken());

                if (prerequisiteIndex == -1) {
                    break;
                }

                buildings[index].remainingBuildingNumber++;
                buildings[prerequisiteIndex - 1].nextBuildings.add(buildings[index]);
            }
        }

        Deque<Building> deque = new ArrayDeque<>();

        for (Building building : buildings) {
            if (building.remainingBuildingNumber == 0) {
                building.remainingBuildingNumber = -1;
                deque.offer(building);
            }
        }

        while (!deque.isEmpty()) {
            Building building = deque.poll();

            for (Building nextBuilding : building.nextBuildings) {
                nextBuilding.prerequisiteTime = Math.max(nextBuilding.prerequisiteTime, building.time + building.prerequisiteTime);
                nextBuilding.remainingBuildingNumber--;

                if (nextBuilding.remainingBuildingNumber == 0) {
                    nextBuilding.remainingBuildingNumber = -1;
                    deque.offer(nextBuilding);
                }
            }
        }

        for (Building building : buildings) {
            out.write(building.time + building.prerequisiteTime + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}