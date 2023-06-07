import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class Solution {
    public List<String> createPostfixNotation(List<String> infixNotation, Map<Character, Integer> operatorPriority) {
        List<String> postfixNotation = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String symbol : infixNotation) {
            if (symbol.charAt(0) == '+' || symbol.charAt(0) == '-' || symbol.charAt(0) == '*') {
                while (!stack.isEmpty() && operatorPriority.get(symbol.charAt(0)) >= operatorPriority.get(stack.peek().charAt(0))) {
                    postfixNotation.add(stack.pop());
                }

                stack.push(symbol);
            } else {
                postfixNotation.add(symbol);
            }
        }

        while (!stack.isEmpty()) {
            postfixNotation.add(stack.pop());
        }

        return postfixNotation;
    }

    public long calculatePostfixNotation(List<String> postfixNotation) {
        Stack<String> stack = new Stack<>();

        for (String symbol : postfixNotation) {
            if (symbol.charAt(0) == '+' || symbol.charAt(0) == '-' || symbol.charAt(0) == '*') {
                long[] operands = {Long.parseLong(stack.pop()), Long.parseLong(stack.pop())};

                switch (symbol.charAt(0)) {
                    case '+':
                        stack.push(String.valueOf(operands[1] + operands[0]));

                        break;

                    case '-':
                        stack.push(String.valueOf(operands[1] - operands[0]));

                        break;

                    case '*':
                        stack.push(String.valueOf(operands[1] * operands[0]));

                        break;
                }
            } else {
                stack.push(symbol);
            }
        }

        return Long.parseLong(stack.pop());
    }

    public long solution(String expression) {
        List<String> infixNotation = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int index = 0; index < expression.length(); index++) {
            char character = expression.charAt(index);

            if (character >= '0' && character <= '9') {
                number.append(character);
            } else {
                infixNotation.add(number.toString());
                infixNotation.add(String.valueOf(character));
                number.setLength(0);
            }
        }

        infixNotation.add(number.toString());

        long answer = 0L;

        answer = Math.max(answer, Math.abs(calculatePostfixNotation(createPostfixNotation(infixNotation, Map.of('+', 1, '-', 2, '*', 3)))));
        answer = Math.max(answer, Math.abs(calculatePostfixNotation(createPostfixNotation(infixNotation, Map.of('+', 1, '-', 3, '*', 2)))));
        answer = Math.max(answer, Math.abs(calculatePostfixNotation(createPostfixNotation(infixNotation, Map.of('+', 2, '-', 1, '*', 3)))));
        answer = Math.max(answer, Math.abs(calculatePostfixNotation(createPostfixNotation(infixNotation, Map.of('+', 2, '-', 3, '*', 1)))));
        answer = Math.max(answer, Math.abs(calculatePostfixNotation(createPostfixNotation(infixNotation, Map.of('+', 3, '-', 1, '*', 2)))));
        answer = Math.max(answer, Math.abs(calculatePostfixNotation(createPostfixNotation(infixNotation, Map.of('+', 3, '-', 2, '*', 1)))));

        return answer;
    }
}