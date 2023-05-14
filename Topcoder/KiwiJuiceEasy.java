public class KiwiJuiceEasy {
    public int[] thePouring(int[] capacities, int[] bottles, int[] fromId, int[] toId) {
        for (int index = 0; index < fromId.length; index++) {
            int amount = Math.min(bottles[fromId[index]], capacities[toId[index]] - bottles[toId[index]]);

            bottles[fromId[index]] -= amount;
            bottles[toId[index]] += amount;
        }

        return bottles;
    }
}