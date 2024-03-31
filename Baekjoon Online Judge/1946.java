import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Applicant implements Comparable<Applicant> {
    public int resumeRanking;
    public int interviewRanking;

    public Applicant(int resumeRanking, int interviewRanking) {
        this.resumeRanking = resumeRanking;
        this.interviewRanking = interviewRanking;
    }

    @Override
    public int compareTo(Applicant o) {
        if (resumeRanking == o.resumeRanking) {
            return Integer.compare(interviewRanking, o.interviewRanking);
        }

        return Integer.compare(resumeRanking, o.resumeRanking);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(in.readLine());

            Applicant[] applicants = new Applicant[N];

            for (int index = 0; index < N; index++) {
                StringTokenizer tokenizer = new StringTokenizer(in.readLine());

                int resumeRanking = Integer.parseInt(tokenizer.nextToken());
                int interviewRanking = Integer.parseInt(tokenizer.nextToken());

                applicants[index] = new Applicant(resumeRanking, interviewRanking);
            }

            Arrays.sort(applicants);

            Applicant lastHiredApplicant = applicants[0];
            int output = 1;

            for (int index = 1; index < N; index++) {
                if (applicants[index].resumeRanking < lastHiredApplicant.resumeRanking || applicants[index].interviewRanking < lastHiredApplicant.interviewRanking) {
                    lastHiredApplicant = applicants[index];
                    output++;
                }
            }

            out.write(output + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }
}