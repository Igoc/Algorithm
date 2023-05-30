import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int conveyorBelt = 1;
        Stack<Integer> auxiliaryConveyorBelt = new Stack<>();

        for (int index = 0; index < order.length; index++) {
            while (conveyorBelt < order[index]) {
                auxiliaryConveyorBelt.push(conveyorBelt++);
            }

            if (conveyorBelt == order[index]) {
                conveyorBelt++;
            } else if (!auxiliaryConveyorBelt.isEmpty() && auxiliaryConveyorBelt.peek() == order[index]) {
                auxiliaryConveyorBelt.pop();
            } else {
                return index;
            }
        }

        return order.length;
    }
}