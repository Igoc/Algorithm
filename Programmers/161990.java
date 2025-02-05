class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0};

        for (int row = 0; row < wallpaper.length; row++) {
            for (int col = 0; col < wallpaper[row].length(); col++) {
                if (wallpaper[row].charAt(col) == '#') {
                    answer[0] = Math.min(answer[0], row);
                    answer[1] = Math.min(answer[1], col);
                    answer[2] = Math.max(answer[2], row + 1);
                    answer[3] = Math.max(answer[3], col + 1);
                }
            }
        }

        return answer;
    }
}