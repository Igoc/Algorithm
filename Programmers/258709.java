import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[] solution(int[][] dice) {
        int[] bestDice = new int[dice.length / 2];

        selectDice(dice, 0, new int[dice.length / 2], 0, new int[dice.length / 2], 0, bestDice, 0);

        for (int index = 0; index < bestDice.length; index++) {
            bestDice[index]++;
        }

        return bestDice;
    }

    public int selectDice(int[][] dice, int diceIndex, int[] aDice, int aDiceIndex, int[] bDice, int bDiceIndex, int[] bestDice, int bestDiceWinsNumber) {
        if (diceIndex == dice.length) {
            List<Integer> aScores = new ArrayList<>();
            List<Integer> bScores = new ArrayList<>();

            calculateScores(dice, aDice, 0, 0, aScores);
            calculateScores(dice, bDice, 0, 0, bScores);

            aScores.sort(Comparator.naturalOrder());

            int winsNumber = 0;

            for (int bScore : bScores) {
                int startIndex = 0;
                int endIndex = aScores.size() - 1;

                while (startIndex <= endIndex) {
                    int midIndex = (startIndex + endIndex) / 2;

                    if (aScores.get(midIndex) <= bScore) {
                        startIndex = midIndex + 1;
                    } else {
                        endIndex = midIndex - 1;
                    }
                }

                winsNumber += aScores.size() - endIndex - 1;
            }

            if (winsNumber > bestDiceWinsNumber) {
                System.arraycopy(aDice, 0, bestDice, 0, bestDice.length);

                return winsNumber;
            }

            return bestDiceWinsNumber;
        }

        if (aDiceIndex < aDice.length) {
            aDice[aDiceIndex] = diceIndex;
            bestDiceWinsNumber = selectDice(dice, diceIndex + 1, aDice, aDiceIndex + 1, bDice, bDiceIndex, bestDice, bestDiceWinsNumber);
        }

        if (bDiceIndex < bDice.length) {
            bDice[bDiceIndex] = diceIndex;
            bestDiceWinsNumber = selectDice(dice, diceIndex + 1, aDice, aDiceIndex, bDice, bDiceIndex + 1, bestDice, bestDiceWinsNumber);
        }

        return bestDiceWinsNumber;
    }

    public void calculateScores(int[][] dice, int[] selectedDice, int selectedDiceIndex, int score, List<Integer> scores) {
        if (selectedDiceIndex == selectedDice.length) {
            scores.add(score);

            return;
        }

        for (int face = 0; face < 6; face++) {
            calculateScores(dice, selectedDice, selectedDiceIndex + 1, score + dice[selectedDice[selectedDiceIndex]][face], scores);
        }
    }
}