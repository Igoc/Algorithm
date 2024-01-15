class Solution {
    public int solution(int n, int[] tops) {
        int[] casesNumber = {2, 1};

        if (tops[0] == 1) {
            casesNumber[0]++;
        }

        for (int index = 1; index < n; index++) {
            int temp = casesNumber[0] + casesNumber[1];

            if (tops[index] == 0) {
                casesNumber[0] = (casesNumber[0] * 2 + casesNumber[1]) % 10007;
            } else {
                casesNumber[0] = (casesNumber[0] * 3 + casesNumber[1] * 2) % 10007;
            }

            casesNumber[1] = temp % 10007;
        }

        return (casesNumber[0] + casesNumber[1]) % 10007;
    }
}