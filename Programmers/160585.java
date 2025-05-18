import java.util.ArrayList;
import java.util.List;

class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public int solution(String[] board) {
        List<Coord> offenses = new ArrayList<>();
        List<Coord> defenses = new ArrayList<>();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[y].charAt(x) == 'O') {
                    offenses.add(new Coord(x, y));
                } else if (board[y].charAt(x) == 'X') {
                    defenses.add(new Coord(x, y));
                }
            }
        }

        if (offenses.size() < defenses.size() || offenses.size() > defenses.size() + 1) {
            return 0;
        }

        return determineGameState(offenses, defenses, new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}, 0);
    }

    public int determineGameState(List<Coord> offenses, List<Coord> defenses, int[][] board, int turn) {
        if (turn == offenses.size() + defenses.size()) {
            return 1;
        }

        if (determineGameEnd(board)) {
            return 0;
        }

        switch (turn % 2) {
            case 0:
                for (Coord offense : offenses) {
                    if (board[offense.y][offense.x] == -1) {
                        board[offense.y][offense.x] = 0;

                        if (determineGameState(offenses, defenses, board, turn + 1) == 1) {
                            return 1;
                        }

                        board[offense.y][offense.x] = -1;
                    }
                }

                break;

            case 1:
                for (Coord defense : defenses) {
                    if (board[defense.y][defense.x] == -1) {
                        board[defense.y][defense.x] = 1;

                        if (determineGameState(offenses, defenses, board, turn + 1) == 1) {
                            return 1;
                        }

                        board[defense.y][defense.x] = -1;
                    }
                }

                break;
        }

        return 0;
    }

    public boolean determineGameEnd(int[][] board) {
        for (int index = 0; index < 3; index++) {
            if (board[index][0] != -1 && board[index][0] == board[index][1] && board[index][1] == board[index][2]) {
                return true;
            }

            if (board[0][index] != -1 && board[0][index] == board[1][index] && board[1][index] == board[2][index]) {
                return true;
            }
        }

        if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }
}