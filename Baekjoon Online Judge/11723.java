import java.io.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int M = Integer.parseInt(in.readLine());

        boolean[] set = new boolean[20];

        for (int index = 0; index < M; index++) {
            String[] operation = in.readLine().split(" ");

            switch (operation[0]) {
                case "add":
                    set[Integer.parseInt(operation[1]) - 1] = true;

                    break;

                case "remove":
                    set[Integer.parseInt(operation[1]) - 1] = false;

                    break;

                case "check":
                    out.write((set[Integer.parseInt(operation[1]) - 1]) ? ("1\n") : ("0\n"));

                    break;

                case "toggle":
                    set[Integer.parseInt(operation[1]) - 1] = !set[Integer.parseInt(operation[1]) - 1];

                    break;

                case "all":
                    for (int number = 0; number < 20; number++) {
                        set[number] = true;
                    }

                    break;

                case "empty":
                    for (int number = 0; number < 20; number++) {
                        set[number] = false;
                    }

                    break;
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}