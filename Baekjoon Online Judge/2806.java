import java.io.*;

public class Main {
    static final int MAX_MUTATIONS = 1000000;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        String dna = in.readLine();

        int[] mutations = new int[3];

        if (dna.charAt(N - 1) == 'A') {
            mutations[1] = mutations[2] = MAX_MUTATIONS + 1;
        } else {
            mutations[0] = MAX_MUTATIONS + 1;
            mutations[1] = mutations[2] = 1;
        }

        for (int index = N - 2; index >= 0; index--) {
            int[] nextMutations = new int[3];

            if (dna.charAt(index) == dna.charAt(index + 1)) {
                nextMutations[0] = Math.min(mutations[0], mutations[2]);
                nextMutations[1] = nextMutations[2] = mutations[1] + 1;
            } else {
                nextMutations[0] = mutations[1];
                nextMutations[1] = nextMutations[2] = Math.min(mutations[0], mutations[2]) + 1;
            }

            mutations = nextMutations;
        }

        out.write(String.valueOf(Math.min(mutations[0], Math.min(mutations[1], mutations[2]))));

        in.close();
        out.flush();
        out.close();
    }
}