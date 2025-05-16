import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        long[] C = Arrays.stream(in.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        out.write(String.valueOf(Math.max(selectFirstRoom(C), deselectFirstRoom(C))));

        in.close();
        out.flush();
        out.close();
    }

    public static long selectFirstRoom(long[] sideRooms) {
        long[][] ants = new long[sideRooms.length][2];

        ants[1][0] = sideRooms[1] + 1;

        for (int index = 2; index < sideRooms.length; index++) {
            ants[index][0] = Math.max(ants[index - 1][0], ants[index - 1][1]) + sideRooms[index];
            ants[index][1] = ants[index - 1][0] + 1;
        }

        return ants[sideRooms.length - 1][0];
    }

    public static long deselectFirstRoom(long[] sideRooms) {
        long[][] ants = new long[sideRooms.length][2];

        ants[0][0] = sideRooms[0];

        for (int index = 1; index < sideRooms.length; index++) {
            ants[index][0] = Math.max(ants[index - 1][0], ants[index - 1][1]) + sideRooms[index];
            ants[index][1] = ants[index - 1][0] + 1;
        }

        return Math.max(ants[sideRooms.length - 1][0], ants[sideRooms.length - 1][1]);
    }
}