import java.util.Stack;

class Stock {
    public int price;
    public int second;

    public Stock(int price, int second) {
        this.price = price;
        this.second = second;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        Stack<Stock> stack = new Stack<>();
        int[] answer = new int[prices.length];

        for (int second = 0; second < prices.length; second++) {
            while (!stack.isEmpty() && stack.peek().price > prices[second]) {
                Stock stock = stack.pop();

                answer[stock.second] = second - stock.second;
            }

            stack.push(new Stock(prices[second], second));
        }

        while (!stack.isEmpty()) {
            Stock stock = stack.pop();

            answer[stock.second] = prices.length - stock.second - 1;
        }

        return answer;
    }
}