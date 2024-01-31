import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> dualPriorityQueue = new LinkedList<>();

        for (String operation : operations) {
            char command = operation.charAt(0);
            int data = Integer.parseInt(operation.substring(2));

            switch (command) {
                case 'I':
                    int leftIndex = 0;
                    int rightIndex = dualPriorityQueue.size() - 1;

                    while (leftIndex <= rightIndex) {
                        int midIndex = (leftIndex + rightIndex) / 2;

                        if (dualPriorityQueue.get(midIndex) < data) {
                            leftIndex = midIndex + 1;
                        } else {
                            rightIndex = midIndex - 1;
                        }
                    }

                    dualPriorityQueue.add(leftIndex, data);

                    break;

                case 'D':
                    if (dualPriorityQueue.isEmpty()) {
                        break;
                    }

                    if (data == 1) {
                        dualPriorityQueue.remove(dualPriorityQueue.size() - 1);
                    } else {
                        dualPriorityQueue.remove(0);
                    }

                    break;
            }
        }

        if (dualPriorityQueue.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{dualPriorityQueue.get(dualPriorityQueue.size() - 1), dualPriorityQueue.get(0)};
    }
}