import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int locomotiveCarriageNumber = Integer.parseInt(in.readLine());

        int[] customers = new int[locomotiveCarriageNumber + 1];

        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        for (int carriage = 1; carriage <= locomotiveCarriageNumber; carriage++) {
            customers[carriage] += Integer.parseInt(tokenizer.nextToken()) + customers[carriage - 1];
        }

        int smallLocomotiveCarriageNumber = Integer.parseInt(in.readLine());

        int[][] table = new int[4][locomotiveCarriageNumber + 1];

        for (int locomotive = 1; locomotive <= 3; locomotive++) {
            for (int carriage = locomotive * smallLocomotiveCarriageNumber; carriage <= locomotiveCarriageNumber; carriage++) {
                table[locomotive][carriage] = Math.max(table[locomotive][carriage - 1], table[locomotive - 1][carriage - smallLocomotiveCarriageNumber] + customers[carriage] - customers[carriage - smallLocomotiveCarriageNumber]);
            }
        }

        out.write(String.valueOf(table[3][locomotiveCarriageNumber]));

        in.close();
        out.flush();
        out.close();
    }
}