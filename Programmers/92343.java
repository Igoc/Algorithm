import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Node {
    public int animal;
    public List<Node> children;

    public Node(int animal) {
        this.animal = animal;
        this.children = new ArrayList<>();
    }
}

class Solution {
    public int calculateMaximumSheepNumber(Node currentNode, List<Node> currentNodes, int sheepNumber, int wolvesNumber, int maximumSheepNumber) {
        sheepNumber += (currentNode.animal == 0) ? (1) : (0);
        wolvesNumber += (currentNode.animal == 1) ? (1) : (0);
        maximumSheepNumber = Math.max(maximumSheepNumber, sheepNumber);

        if (sheepNumber <= wolvesNumber) {
            return maximumSheepNumber;
        }

        currentNodes.addAll(currentNode.children);

        for (int index = 0; index < currentNodes.size(); index++) {
            List<Node> nextNodes = new LinkedList<>(currentNodes);
            Node nextNode = nextNodes.remove(index);

            maximumSheepNumber = calculateMaximumSheepNumber(nextNode, nextNodes, sheepNumber, wolvesNumber, maximumSheepNumber);
        }

        return maximumSheepNumber;
    }

    public int solution(int[] info, int[][] edges) {
        Node[] nodes = new Node[info.length];

        for (int index = 0; index < nodes.length; index++) {
            nodes[index] = new Node(info[index]);
        }

        for (int[] edge : edges) {
            nodes[edge[0]].children.add(nodes[edge[1]]);
        }

        return calculateMaximumSheepNumber(nodes[0], new LinkedList<>(), 0, 0, 0);
    }
}