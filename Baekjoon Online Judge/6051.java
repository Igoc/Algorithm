import java.io.*;
import java.util.StringTokenizer;

class Timeline {
    public int problem;
    public int penultimateRecordQuery;
    public int lastRecordQuery;

    public Timeline(int problem, int penultimateRecordQuery, int lastRecordQuery) {
        this.problem = problem;
        this.penultimateRecordQuery = penultimateRecordQuery;
        this.lastRecordQuery = lastRecordQuery;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());

        Timeline[] timelines = new Timeline[N + 1];
        timelines[0] = new Timeline(-1, 0, 0);

        for (int Q = 1; Q <= N; Q++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            char c = tokenizer.nextToken().charAt(0);

            switch (c) {
                case 'a':
                    timelines[Q] = new Timeline(Integer.parseInt(tokenizer.nextToken()), timelines[Q - 1].lastRecordQuery, Q);
                    break;

                case 's':
                    timelines[Q] = timelines[timelines[Q - 1].penultimateRecordQuery];
                    break;

                case 't':
                    timelines[Q] = timelines[Integer.parseInt(tokenizer.nextToken()) - 1];
                    break;
            }

            out.write(timelines[Q].problem + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}