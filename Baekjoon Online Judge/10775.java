import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int findGate(int g, int[] gates) {
        if (g == gates[g]) {
            return g;
        }

        return gates[g] = findGate(gates[g], gates);
    }

    public static void main(String[] args) throws IOException {
        int G = Integer.parseInt(in.readLine());
        int P = Integer.parseInt(in.readLine());

        int[] gates = new int[G + 1];

        for (int index = 0; index <= G; index++) {
            gates[index] = index;
        }

        int output = 0;

        for (int index = 0; index < P; index++) {
            int g = findGate(Integer.parseInt(in.readLine()), gates);

            if (g == 0) {
                break;
            }

            gates[g] = g - 1;
            output++;
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}