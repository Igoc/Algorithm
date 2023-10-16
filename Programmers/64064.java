import java.util.*;

class Node {
    public char character;
    public Map<Character, Node> children;

    public Node(char character) {
        this.character = character;
        this.children = new HashMap<>();
    }
}

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        Node root = new Node('\0');

        for (String userId : user_id) {
            Node node = root;

            for (char character : userId.toCharArray()) {
                if (!node.children.containsKey(character)) {
                    node.children.put(character, new Node(character));
                }

                node = node.children.get(character);
            }

            node.children.put('\0', null);
        }

        List<List<String>> bannedIdsList = new ArrayList<>();

        for (String bannedId : banned_id) {
            bannedIdsList.add(findBannedIds(bannedId, new ArrayList<>(), new StringBuilder(), root));
        }

        return combineBannedIdsList(bannedIdsList, new HashSet<>(), new HashSet<>(), 0).size();
    }

    public List<String> findBannedIds(String bannedId, List<String> bannedIds, StringBuilder id, Node node) {
        if (id.length() == bannedId.length()) {
            if (node.children.containsKey('\0')) {
                bannedIds.add(id.toString());
            }

            return bannedIds;
        }

        char character = bannedId.charAt(id.length());

        if (character == '*') {
            for (Node child : node.children.values()) {
                if (child != null) {
                    id.append(child.character);
                    bannedIds = findBannedIds(bannedId, bannedIds, id, child);
                    id.deleteCharAt(id.length() - 1);
                }
            }
        } else if (node.children.containsKey(character)) {
            id.append(character);
            bannedIds = findBannedIds(bannedId, bannedIds, id, node.children.get(character));
            id.deleteCharAt(id.length() - 1);
        }

        return bannedIds;
    }

    public Set<Set<String>> combineBannedIdsList(List<List<String>> bannedIdsList, Set<Set<String>> combinations, Set<String> combination, int index) {
        if (index == bannedIdsList.size()) {
            if (combination.size() == bannedIdsList.size()) {
                combinations.add(combination);
            }

            return combinations;
        }

        for (String bannedId : bannedIdsList.get(index)) {
            Set<String> currentCombination = new HashSet<>(combination);

            currentCombination.add(bannedId);
            combinations = combineBannedIdsList(bannedIdsList, combinations, currentCombination, index + 1);
        }

        return combinations;
    }
}