import java.util.Arrays;

class Food implements Comparable<Food> {
    public int index;
    public int time;

    public Food(int index, int time) {
        this.index = index;
        this.time = time;
    }

    @Override
    public int compareTo(Food o) {
        return Integer.compare(time, o.time);
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        Food[] foods = new Food[food_times.length];

        for (int index = 0; index < foods.length; index++) {
            foods[index] = new Food(index, food_times[index]);
        }

        Arrays.sort(foods);

        int minimumFoodTime = 0;
        long currentTime = 0L;

        boolean[] completion = new boolean[foods.length];
        int completionNumber = 0;

        foodLoop:
        for (Food food : foods) {
            int additionalTime = food.time - minimumFoodTime;

            for (int time = 0; time < additionalTime; time++) {
                if (currentTime + foods.length - completionNumber > k) {
                    break foodLoop;
                }

                minimumFoodTime++;
                currentTime += foods.length - completionNumber;
            }

            completion[food.index] = true;
            completionNumber++;
        }

        for (int index = 0; index < foods.length; index++) {
            if (completion[index]) {
                continue;
            }

            if (currentTime == k) {
                return index + 1;
            }

            currentTime++;
        }

        return -1;
    }
}