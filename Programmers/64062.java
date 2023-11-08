class SegmentTree {
    public int startIndex;
    public int endIndex;
    public int maximumValue;
    public SegmentTree leftChild;
    public SegmentTree rightChild;

    public SegmentTree(int startIndex, int endIndex, int[] values) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;

        if (startIndex == endIndex) {
            this.maximumValue = values[startIndex];
        } else {
            int midIndex = (startIndex + endIndex) / 2;

            this.leftChild = new SegmentTree(startIndex, midIndex, values);
            this.rightChild = new SegmentTree(midIndex + 1, endIndex, values);
            this.maximumValue = Math.max(leftChild.maximumValue, rightChild.maximumValue);
        }
    }

    public int findMaximumValue(int startIndex, int endIndex) {
        if (startIndex > this.endIndex || endIndex < this.startIndex) {
            return 0;
        }

        if (startIndex <= this.startIndex && endIndex >= this.endIndex) {
            return maximumValue;
        }

        int leftMaximumValue = leftChild.findMaximumValue(startIndex, endIndex);
        int rightMaximumValue = rightChild.findMaximumValue(startIndex, endIndex);

        return Math.max(leftMaximumValue, rightMaximumValue);
    }
}

class Solution {
    public int solution(int[] stones, int k) {
        SegmentTree segmentTree = new SegmentTree(0, stones.length - 1, stones);
        int answer = Integer.MAX_VALUE;

        for (int index = 0; index <= stones.length - k; index++) {
            answer = Math.min(answer, segmentTree.findMaximumValue(index, index + k - 1));
        }

        return answer;
    }
}