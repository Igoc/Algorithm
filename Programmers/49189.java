import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    public int number;
    public int distance;
    public List<Node> siblings;

    public Node(int number, int distance) {
        this(number, distance, new ArrayList<>());
    }

    public Node(int number, int distance, List<Node> siblings) {
        this.number = number;
        this.distance = distance;
        this.siblings = siblings;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(distance, o.distance);
    }
}

class Solution {
    public int solution(int n, int[][] edge) {
        Node[] nodes = new Node[n];

        for (int index = 0; index < n; index++) {
            nodes[index] = new Node(index, 0);
        }

        for (int[] connection : edge) {
            nodes[connection[0] - 1].siblings.add(nodes[connection[1] - 1]);
            nodes[connection[1] - 1].siblings.add(nodes[connection[0] - 1]);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        boolean[] visit = new boolean[n];

        priorityQueue.offer(nodes[0]);

        int maximumDistance = 0;
        int answer = 0;

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();

            if (visit[node.number]) {
                continue;
            }

            visit[node.number] = true;

            if (node.distance > maximumDistance) {
                maximumDistance = node.distance;
                answer = 0;
            }

            if (node.distance == maximumDistance) {
                answer++;
            }

            for (Node sibling : node.siblings) {
                if (!visit[sibling.number]) {
                    priorityQueue.offer(new Node(sibling.number, node.distance + 1, sibling.siblings));
                }
            }
        }

        return answer;
    }
}