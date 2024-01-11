import java.util.ArrayDeque;
import java.util.Deque;

class Pair {
    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;

        boolean[] holdingCards = new boolean[n + 1];
        int pairCardsNumber = 0;

        for (int index = 0; index < n / 3; index++) {
            if (holdingCards[n - cards[index] + 1]) {
                holdingCards[n - cards[index] + 1] = false;
                pairCardsNumber++;
            } else {
                holdingCards[cards[index]] = true;
            }
        }

        boolean[] spareCards = new boolean[n + 1];
        Deque<Pair> sparePairCards = new ArrayDeque<>();
        int answer = 1;

        while (answer <= n / 3) {
            int firstCard = cards[n / 3 + answer * 2 - 2];
            int secondCard = cards[n / 3 + answer * 2 - 1];

            if (coin >= 1 && holdingCards[n - firstCard + 1]) {
                holdingCards[n - firstCard + 1] = false;
                pairCardsNumber++;
                coin--;
            } else {
                if (spareCards[n - firstCard + 1]) {
                    spareCards[n - firstCard + 1] = false;
                    sparePairCards.add(new Pair(firstCard, n - firstCard + 1));
                } else {
                    spareCards[firstCard] = true;
                }
            }

            if (coin >= 1 && holdingCards[n - secondCard + 1]) {
                holdingCards[n - secondCard + 1] = false;
                pairCardsNumber++;
                coin--;
            } else {
                if (spareCards[n - secondCard + 1]) {
                    spareCards[n - secondCard + 1] = false;
                    sparePairCards.add(new Pair(secondCard, n - secondCard + 1));
                } else {
                    spareCards[secondCard] = true;
                }
            }

            if (pairCardsNumber == 0) {
                if (coin < 2 || sparePairCards.isEmpty()) {
                    break;
                }

                sparePairCards.pop();
                coin -= 2;
            } else {
                pairCardsNumber--;
            }

            answer++;
        }

        return answer;
    }
}