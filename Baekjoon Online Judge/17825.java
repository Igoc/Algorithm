import java.io.*;
import java.util.Arrays;

class Game {
    public int[] scores;
    public int[][] moves;

    public Game() {
        this.scores = initializeScores();
        this.moves = initializeMoves();
    }

    public static int[] initializeScores() {
        int[] scores = new int[33];

        for (int space = 1; space <= 20; space++) {
            scores[space] = space * 2;
        }

        for (int space = 21; space <= 23; space++) {
            scores[space] = (space - 20) * 3 + 10;
        }

        for (int space = 24; space <= 25; space++) {
            scores[space] = (space - 23) * 2 + 20;
        }

        for (int space = 26; space <= 28; space++) {
            scores[space] = 54 - space;
        }

        for (int space = 29; space <= 31; space++) {
            scores[space] = (space - 28) * 5 + 20;
        }

        return scores;
    }

    public static int[][] initializeMoves() {
        int[][] moves = new int[33][5];

        for (int space = 0; space <= 20; space++) {
            for (int side = 0; side < 5; side++) {
                moves[space][side] = (space + side + 1 <= 20) ? (space + side + 1) : (32);
            }
        }

        moves[5][0] = 21;
        moves[5][1] = 22;
        moves[5][2] = 23;
        moves[5][3] = 29;
        moves[5][4] = 30;

        moves[10][0] = 24;
        moves[10][1] = 25;
        moves[10][2] = 29;
        moves[10][3] = 30;
        moves[10][4] = 31;

        for (int side = 0; side < 5; side++) {
            moves[15][side] = side + 26;
        }

        moves[21][0] = 22;
        moves[21][1] = 23;
        moves[21][2] = 29;
        moves[21][3] = 30;
        moves[21][4] = 31;

        moves[22][0] = 23;
        moves[22][1] = 29;
        moves[22][2] = 30;
        moves[22][3] = 31;
        moves[22][4] = 20;

        moves[23][0] = 29;
        moves[23][1] = 30;
        moves[23][2] = 31;
        moves[23][3] = 20;
        moves[23][4] = 32;

        moves[24][0] = 25;
        moves[24][1] = 29;
        moves[24][2] = 30;
        moves[24][3] = 31;
        moves[24][4] = 20;

        moves[25][0] = 29;
        moves[25][1] = 30;
        moves[25][2] = 31;
        moves[25][3] = 20;
        moves[25][4] = 32;

        for (int side = 0; side < 5; side++) {
            moves[26][side] = side + 27;
        }

        moves[27][0] = 28;
        moves[27][1] = 29;
        moves[27][2] = 30;
        moves[27][3] = 31;
        moves[27][4] = 20;

        moves[28][0] = 29;
        moves[28][1] = 30;
        moves[28][2] = 31;
        moves[28][3] = 20;
        moves[28][4] = 32;

        for (int space = 29; space <= 32; space++) {
            for (int side = 0; side < 5; side++) {
                moves[space][side] = space + side + 1;

                if (moves[space][side] == 32) {
                    moves[space][side] = 20;
                } else if (moves[space][side] >= 33) {
                    moves[space][side] = 32;
                }
            }
        }

        return moves;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] dices = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(side -> side - 1)
                .toArray();

        out.write(String.valueOf(calculateMaximumScore(new Game(), dices, new int[4], 0, 0, 0)));

        in.close();
        out.flush();
        out.close();
    }

    public static int calculateMaximumScore(Game game, int[] dices, int[] spaces, int sequence, int score, int maximumScore) {
        if (sequence == dices.length) {
            return Math.max(score, maximumScore);
        }

        int side = dices[sequence];

        for (int piece = 0; piece < 4; piece++) {
            int currentSpace = spaces[piece];
            int nextSpace = game.moves[currentSpace][side];
            boolean overlap = false;

            for (int otherPiece = 0; otherPiece < 4; otherPiece++) {
                if (piece != otherPiece && nextSpace == spaces[otherPiece] && nextSpace != 32) {
                    overlap = true;
                    break;
                }
            }

            if (currentSpace == 32 || overlap) {
                continue;
            }

            spaces[piece] = nextSpace;
            maximumScore = calculateMaximumScore(game, dices, spaces, sequence + 1, score + game.scores[nextSpace], maximumScore);
            spaces[piece] = currentSpace;
        }

        return maximumScore;
    }
}