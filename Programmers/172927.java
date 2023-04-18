class Solution {
    public int calculateMinFatigue(int[] pickaxes, int pickaxeNumber, String[] minerals, int fatigue, int minFatigue, int depth) {
        if (pickaxeNumber == 0 || depth >= minerals.length) {
            return Math.min(fatigue, minFatigue);
        }

        for (int pickaxeIndex = 0; pickaxeIndex < pickaxes.length; pickaxeIndex++) {
            if (pickaxes[pickaxeIndex] != 0) {
                int currentFatigue = fatigue;

                for (int mineralIndex = depth; mineralIndex < Math.min(minerals.length, depth + 5); mineralIndex++) {
                    currentFatigue += getFatigue(pickaxeIndex, minerals[mineralIndex]);
                }

                pickaxes[pickaxeIndex]--;
                minFatigue = calculateMinFatigue(pickaxes, pickaxeNumber - 1, minerals, currentFatigue, minFatigue, depth + 5);
                pickaxes[pickaxeIndex]++;
            }
        }

        return minFatigue;
    }

    public int getFatigue(int pickaxe, String mineral) {
        switch (pickaxe) {
            case 1:
                if (mineral.equals("diamond")) {
                    return 5;
                }

                break;

            case 2:
                if (mineral.equals("diamond")) {
                    return 25;
                } else if (mineral.equals("iron")) {
                    return 5;
                }

                break;
        }

        return 1;
    }

    public int solution(int[] picks, String[] minerals) {
        int pickaxeNumber = 0;

        for (int pick : picks) {
            pickaxeNumber += pick;
        }

        return calculateMinFatigue(picks, pickaxeNumber, minerals, 0, Integer.MAX_VALUE, 0);
    }
}