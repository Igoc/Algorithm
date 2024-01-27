import java.io.*;
import java.util.Arrays;

class Campus {
    public static final int INFORMATION_SCIENCE_BUILDING = 0;
    public static final int COMPUTER_INSTITUTE = 1;
    public static final int SHINYANG_HALL = 2;
    public static final int VERITAS_HALL = 3;
    public static final int STUDENT_UNION = 4;
    public static final int VISION_HALL = 5;
    public static final int HANKYUNGCHIK_MEMORIAL_HALL = 6;
    public static final int HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING = 7;
}

public class Main {
    static final int MODULO_NUMBER = 1000000007;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int D = Integer.parseInt(in.readLine());

        int[][] pathNumbers = new int[2][8];

        pathNumbers[0][Campus.INFORMATION_SCIENCE_BUILDING] = 1;

        for (int minute = 1; minute <= D; minute++) {
            Arrays.fill(pathNumbers[minute % 2], 0);

            pathNumbers[minute % 2][Campus.INFORMATION_SCIENCE_BUILDING] += pathNumbers[(minute + 1) % 2][Campus.COMPUTER_INSTITUTE];
            pathNumbers[minute % 2][Campus.INFORMATION_SCIENCE_BUILDING] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.INFORMATION_SCIENCE_BUILDING] += pathNumbers[(minute + 1) % 2][Campus.VISION_HALL];
            pathNumbers[minute % 2][Campus.INFORMATION_SCIENCE_BUILDING] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.COMPUTER_INSTITUTE] += pathNumbers[(minute + 1) % 2][Campus.INFORMATION_SCIENCE_BUILDING];
            pathNumbers[minute % 2][Campus.COMPUTER_INSTITUTE] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.COMPUTER_INSTITUTE] += pathNumbers[(minute + 1) % 2][Campus.SHINYANG_HALL];
            pathNumbers[minute % 2][Campus.COMPUTER_INSTITUTE] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.COMPUTER_INSTITUTE] += pathNumbers[(minute + 1) % 2][Campus.VISION_HALL];
            pathNumbers[minute % 2][Campus.COMPUTER_INSTITUTE] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.SHINYANG_HALL] += pathNumbers[(minute + 1) % 2][Campus.COMPUTER_INSTITUTE];
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] += pathNumbers[(minute + 1) % 2][Campus.VERITAS_HALL];
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] += pathNumbers[(minute + 1) % 2][Campus.VISION_HALL];
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] += pathNumbers[(minute + 1) % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL];
            pathNumbers[minute % 2][Campus.SHINYANG_HALL] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.VERITAS_HALL] += pathNumbers[(minute + 1) % 2][Campus.SHINYANG_HALL];
            pathNumbers[minute % 2][Campus.VERITAS_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.VERITAS_HALL] += pathNumbers[(minute + 1) % 2][Campus.STUDENT_UNION];
            pathNumbers[minute % 2][Campus.VERITAS_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.VERITAS_HALL] += pathNumbers[(minute + 1) % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL];
            pathNumbers[minute % 2][Campus.VERITAS_HALL] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.STUDENT_UNION] += pathNumbers[(minute + 1) % 2][Campus.VERITAS_HALL];
            pathNumbers[minute % 2][Campus.STUDENT_UNION] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.STUDENT_UNION] += pathNumbers[(minute + 1) % 2][Campus.HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING];
            pathNumbers[minute % 2][Campus.STUDENT_UNION] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.VISION_HALL] += pathNumbers[(minute + 1) % 2][Campus.INFORMATION_SCIENCE_BUILDING];
            pathNumbers[minute % 2][Campus.VISION_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.VISION_HALL] += pathNumbers[(minute + 1) % 2][Campus.COMPUTER_INSTITUTE];
            pathNumbers[minute % 2][Campus.VISION_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.VISION_HALL] += pathNumbers[(minute + 1) % 2][Campus.SHINYANG_HALL];
            pathNumbers[minute % 2][Campus.VISION_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.VISION_HALL] += pathNumbers[(minute + 1) % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL];
            pathNumbers[minute % 2][Campus.VISION_HALL] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] += pathNumbers[(minute + 1) % 2][Campus.SHINYANG_HALL];
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] += pathNumbers[(minute + 1) % 2][Campus.VERITAS_HALL];
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] += pathNumbers[(minute + 1) % 2][Campus.VISION_HALL];
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] += pathNumbers[(minute + 1) % 2][Campus.HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING];
            pathNumbers[minute % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL] %= MODULO_NUMBER;

            pathNumbers[minute % 2][Campus.HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING] += pathNumbers[(minute + 1) % 2][Campus.STUDENT_UNION];
            pathNumbers[minute % 2][Campus.HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING] %= MODULO_NUMBER;
            pathNumbers[minute % 2][Campus.HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING] += pathNumbers[(minute + 1) % 2][Campus.HANKYUNGCHIK_MEMORIAL_HALL];
            pathNumbers[minute % 2][Campus.HYUNGNAM_MEMORIAL_ENGINEERING_BUILDING] %= MODULO_NUMBER;
        }

        out.write(String.valueOf(pathNumbers[D % 2][Campus.INFORMATION_SCIENCE_BUILDING]));

        in.close();
        out.flush();
        out.close();
    }
}