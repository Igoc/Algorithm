import java.util.HashMap;
import java.util.Map;

class Seller {
    public int income;
    public Seller referral;
}

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Seller> sellers = new HashMap<>();

        sellers.put("-", new Seller());

        for (int index = 0; index < enroll.length; index++) {
            sellers.put(enroll[index], new Seller());
            sellers.get(enroll[index]).referral = sellers.get(referral[index]);
        }

        for (int index = 0; index < seller.length; index++) {
            Seller currentSeller = sellers.get(seller[index]);
            int income = amount[index] * 100;

            while (currentSeller != null) {
                int distributedIncome = (int) (income * 0.1);

                currentSeller.income += income - distributedIncome;
                income = distributedIncome;
                currentSeller = currentSeller.referral;
            }
        }

        int[] answer = new int[enroll.length];

        for (int index = 0; index < enroll.length; index++) {
            answer[index] = sellers.get(enroll[index]).income;
        }

        return answer;
    }
}