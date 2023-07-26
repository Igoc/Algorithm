class Solution {
    public int solution(int[][] triangle) {
        int[][] sum = new int[triangle.length][];

        for (int level = 0; level < triangle.length; level++) {
            sum[level] = triangle[level].clone();
        }

        for (int level = 0; level < triangle.length - 1; level++) {
            for (int index = 0; index < triangle[level].length; index++) {
                sum[level + 1][index] = Math.max(sum[level + 1][index], triangle[level + 1][index] + sum[level][index]);
                sum[level + 1][index + 1] = Math.max(sum[level + 1][index + 1], triangle[level + 1][index + 1] + sum[level][index]);
            }
        }

        int answer = 0;

        for (int number : sum[sum.length - 1]) {
            answer = Math.max(answer, number);
        }

        return answer;
    }
}