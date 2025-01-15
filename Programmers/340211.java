import java.util.ArrayDeque;
import java.util.Queue;

class Robot {
    public int index;
    public int row;
    public int col;
    public int routeIndex;

    public Robot(int index, int row, int col, int routeIndex) {
        this.index = index;
        this.row = row;
        this.col = col;
        this.routeIndex = routeIndex;
    }
}

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Queue<Robot> queue = new ArrayDeque<>();
        int answer = 0;

        answer += initialize(points, routes, queue);
        answer += simulate(points, routes, queue);

        return answer;
    }

    public int initialize(int[][] points, int[][] routes, Queue<Robot> queue) {
        int[][] counts = new int[101][101];
        int answer = 0;

        for (int robot = 0; robot < routes.length; robot++) {
            int route = routes[robot][0] - 1;
            int row = points[route][0];
            int col = points[route][1];

            queue.add(new Robot(robot, row, col, 1));
            counts[row][col]++;

            if (counts[row][col] == 2) {
                answer++;
            }
        }

        return answer;
    }

    public int simulate(int[][] points, int[][] routes, Queue<Robot> queue) {
        int answer = 0;

        while (!queue.isEmpty()) {
            Queue<Robot> nextQueue = new ArrayDeque<>();
            int[][] counts = new int[101][101];

            for (Robot robot : queue) {
                int[] targetPoint = points[routes[robot.index][robot.routeIndex] - 1];

                if (targetPoint[0] != robot.row) {
                    robot.row += (targetPoint[0] > robot.row) ? (1) : (-1);
                } else if (targetPoint[1] != robot.col) {
                    robot.col += (targetPoint[1] > robot.col) ? (1) : (-1);
                }

                if (targetPoint[0] == robot.row && targetPoint[1] == robot.col) {
                    robot.routeIndex++;
                }

                if (robot.routeIndex < routes[0].length) {
                    nextQueue.add(robot);
                }

                counts[robot.row][robot.col]++;

                if (counts[robot.row][robot.col] == 2) {
                    answer++;
                }
            }

            queue = nextQueue;
        }

        return answer;
    }
}