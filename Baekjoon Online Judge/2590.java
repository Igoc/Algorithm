import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] papers = new int[7];

        for (int size = 1; size <= 6; size++) {
            papers[size] = Integer.parseInt(in.readLine());
        }

        int output = 0;

        output += handleSixthPaper(papers);
        output += handleFifthPaper(papers);
        output += handleFourthPaper(papers);
        output += handleThirdPaper(papers);
        output += handleSecondPaper(papers);
        output += handleFirstPaper(papers);

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }

    public static int handleSixthPaper(int[] papers) {
        int result = papers[6];

        papers[6] = 0;

        return result;
    }

    public static int handleFifthPaper(int[] papers) {
        int result = papers[5];

        while (papers[5] >= 1) {
            papers[1] -= Math.min(papers[1], 11);
            papers[5]--;
        }

        return result;
    }

    public static int handleFourthPaper(int[] papers) {
        int result = papers[4];

        while (papers[4] >= 1) {
            papers[1] -= Math.min(papers[1], 20 - Math.min(papers[2], 5) * 4);
            papers[2] -= Math.min(papers[2], 5);
            papers[4]--;
        }

        return result;
    }

    public static int handleThirdPaper(int[] papers) {
        int result = papers[3] / 4;

        papers[3] %= 4;

        switch (papers[3]) {
            case 0:
                return result;

            case 1:
                papers[1] -= Math.min(papers[1], 27 - Math.min(papers[2], 5) * 4);
                papers[2] -= Math.min(papers[2], 5);
                break;

            case 2:
                papers[1] -= Math.min(papers[1], 18 - Math.min(papers[2], 3) * 4);
                papers[2] -= Math.min(papers[2], 3);
                break;

            case 3:
                papers[1] -= Math.min(papers[1], 9 - Math.min(papers[2], 1) * 4);
                papers[2] -= Math.min(papers[2], 1);
                break;
        }

        papers[3] = 0;

        return result + 1;
    }

    public static int handleSecondPaper(int[] papers) {
        int result = papers[2] / 9;

        papers[2] %= 9;

        if (papers[2] == 0) {
            return result;
        }

        papers[1] -= Math.min(papers[1], 36 - papers[2] * 4);
        papers[2] = 0;

        return result + 1;
    }

    public static int handleFirstPaper(int[] papers) {
        int result = papers[1] / 36;

        papers[1] %= 36;

        if (papers[1] == 0) {
            return result;
        }

        papers[1] = 0;

        return result + 1;
    }
}