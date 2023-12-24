class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int index = 0; index < numbers.length; index++) {
            String binaryNumber = Long.toBinaryString(numbers[index]);

            for (int level = 1; level <= 6; level++) {
                int perfectBinaryTreeNodeNumber = (int) Math.pow(2, level) - 1;

                if (binaryNumber.length() <= perfectBinaryTreeNodeNumber) {
                    binaryNumber = "0".repeat(perfectBinaryTreeNodeNumber - binaryNumber.length()) + binaryNumber;

                    break;
                }
            }

            answer[index] = determineRepresentableBinaryTree(binaryNumber);
        }

        return answer;
    }

    public int determineRepresentableBinaryTree(String binaryNumber) {
        if (binaryNumber.length() == 1) {
            return 1;
        }

        int leftIndex = (binaryNumber.length() + 1) / 4 - 1;
        int midIndex = (binaryNumber.length() + 1) / 2 - 1;
        int rightIndex = (binaryNumber.length() + 1) * 3 / 4 - 1;

        if (binaryNumber.charAt(midIndex) == '0' && (binaryNumber.charAt(leftIndex) == '1' || binaryNumber.charAt(rightIndex) == '1')) {
            return 0;
        }

        return determineRepresentableBinaryTree(binaryNumber.substring(0, midIndex)) & determineRepresentableBinaryTree(binaryNumber.substring(midIndex + 1));
    }
}