import java.util.*;

class Plan {
    public String name;
    public int startTime;
    public int playTime;

    public Plan(String name, int startTime, int playTime) {
        this.name = name;
        this.startTime = startTime;
        this.playTime = playTime;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        List<Plan> planList = new ArrayList<>();

        for (String[] plan : plans) {
            String[] startTime = plan[1].split(":");

            planList.add(new Plan(plan[0], Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]), Integer.parseInt(plan[2])));
        }

        planList.sort(Comparator.comparingInt(o -> o.startTime));

        Stack<Plan> suspendedPlans = new Stack<>();
        List<String> answer = new ArrayList<>();

        for (int index = 1; index < planList.size(); index++) {
            Plan previousPlan = planList.get(index - 1);
            Plan currentPlan = planList.get(index);

            if (previousPlan.startTime + previousPlan.playTime <= currentPlan.startTime) {
                int remainingTime = currentPlan.startTime - previousPlan.startTime - previousPlan.playTime;

                answer.add(previousPlan.name);

                while (!suspendedPlans.isEmpty()) {
                    Plan suspendedPlan = suspendedPlans.peek();

                    if (suspendedPlan.playTime <= remainingTime) {
                        answer.add(suspendedPlans.pop().name);
                        remainingTime -= suspendedPlan.playTime;
                    } else {
                        suspendedPlan.playTime -= remainingTime;

                        break;
                    }
                }
            } else {
                previousPlan.playTime = previousPlan.startTime + previousPlan.playTime - currentPlan.startTime;
                suspendedPlans.push(previousPlan);
            }
        }

        answer.add(planList.get(planList.size() - 1).name);

        while (!suspendedPlans.isEmpty()) {
            answer.add(suspendedPlans.pop().name);
        }

        return answer.toArray(String[]::new);
    }
}