class Solution {
    public int solution(String skill, String[] skill_trees) {
        char[] priorSkills = new char[26];

        for (int index = skill.length() - 1; index > 0; index--) {
            priorSkills[skill.charAt(index) - 'A'] = skill.charAt(index - 1);
        }

        int answer = 0;

        for (String skillTree : skill_trees) {
            boolean[] acquiredSkills = new boolean[26];
            boolean possibleSkillTree = true;

            for (int index = 0; index < skillTree.length(); index++) {
                if (priorSkills[skillTree.charAt(index) - 'A'] != 0 && !acquiredSkills[priorSkills[skillTree.charAt(index) - 'A'] - 'A']) {
                    possibleSkillTree = false;

                    break;
                }

                acquiredSkills[skillTree.charAt(index) - 'A'] = true;
            }

            if (possibleSkillTree) {
                answer++;
            }
        }

        return answer;
    }
}