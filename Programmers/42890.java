import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> findCandidateKeys(String[][] relation, int key, List<Integer> candidateKeys, int depth) {
        if (depth >= relation[0].length) {
            if (key == 0) {
                return candidateKeys;
            }

            for (int candidateKey : candidateKeys) {
                if ((key & candidateKey) == candidateKey) {
                    return candidateKeys;
                }
            }

            Set<String> rows = new HashSet<>();

            for (int row = 0; row < relation.length; row++) {
                StringBuilder tuple = new StringBuilder();

                for (int col = 0; col < relation[row].length; col++) {
                    if ((key & (int) Math.pow(2, col)) != 0) {
                        tuple.append(relation[row][col]).append(" ");
                    }
                }

                rows.add(tuple.toString());
            }

            if (rows.size() == relation.length) {
                candidateKeys.add(key);
            }

            return candidateKeys;
        }

        candidateKeys = findCandidateKeys(relation, key, candidateKeys, depth + 1);
        candidateKeys = findCandidateKeys(relation, key + (int) Math.pow(2, depth), candidateKeys, depth + 1);

        return candidateKeys;
    }

    public int solution(String[][] relation) {
        return findCandidateKeys(relation, 0, new ArrayList<>(), 0).size();
    }
}