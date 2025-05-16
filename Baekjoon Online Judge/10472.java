import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static final int[] DX = {0, 0, 1, 0, -1};
    static final int[] DY = {0, -1, 0, 1, 0};

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Map<String, Integer> boards = initializeBoards();

        int P = Integer.parseInt(in.readLine());

        while (P-- > 0) {
            String input = in.readLine() + in.readLine() + in.readLine();

            out.write(boards.get(input) + "\n");
        }

        in.close();
        out.flush();
        out.close();
    }

    public static Map<String, Integer> initializeBoards() {
        Deque<String> deque = new ArrayDeque<>();
        Map<String, Integer> boards = new HashMap<>();

        deque.offer(".........");
        boards.put(".........", 0);

        while (!deque.isEmpty()) {
            String board = deque.poll();

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    StringBuilder nextBoard = new StringBuilder(board);

                    for (int dir = 0; dir < 5; dir++) {
                        int x = col + DX[dir];
                        int y = row + DY[dir];

                        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                            int index = y * 3 + x;

                            if (nextBoard.charAt(index) == '.') {
                                nextBoard.setCharAt(index, '*');
                            } else {
                                nextBoard.setCharAt(index, '.');
                            }
                        }
                    }

                    if (!boards.containsKey(nextBoard.toString())) {
                        deque.offer(nextBoard.toString());
                        boards.put(nextBoard.toString(), boards.get(board) + 1);
                    }
                }
            }
        }

        return boards;
    }
}