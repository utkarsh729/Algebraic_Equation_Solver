package com.example.algebraic_equation_solver.util;


import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostfixConverter {

    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/", "^");

    private static final Map<String, Integer> precedence = Map.of(
            "+", 1, "-", 1,
            "*", 2, "/", 2,
            "^", 3
    );

    public static void validate(String infix) {
        if (infix == null || infix.trim().isEmpty()) {
            throw new IllegalArgumentException("Equation must not be empty");
        }

        StringTokenizer tokens = new StringTokenizer(infix, "+-*/^() ", true);

        boolean expectOperand = true;
        int parentheses = 0;

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.equals("(")) {
                parentheses++;
                expectOperand = true;
            } else if (token.equals(")")) {
                parentheses--;
                if (parentheses < 0) throw new IllegalArgumentException("Mismatched parentheses");
                expectOperand = false;
            } else if (OPERATORS.contains(token)) {
                if (expectOperand && !token.equals("-")) {
                    throw new IllegalArgumentException("Invalid syntax: unexpected operator '" + token + "'");
                }
                expectOperand = true;
            } else if (token.matches("[a-zA-Z0-9]+")) {
                if (!expectOperand) throw new IllegalArgumentException("Invalid syntax: unexpected operand '" + token + "'");
                expectOperand = false;
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        if (parentheses != 0) throw new IllegalArgumentException("Mismatched parentheses");
        if (expectOperand) throw new IllegalArgumentException("Equation cannot end with operator");
    }

    public static String convert(String infix) {
        // Validate first
        validate(infix);

        StringTokenizer tokens = new StringTokenizer(infix, "+-*/^() ", true);
        StringBuilder output = new StringBuilder();
        Stack<String> stack = new Stack<>();

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.matches("[a-zA-Z0-9]+")) {
                output.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("("))
                    output.append(stack.pop()).append(" ");
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence.containsKey(stack.peek())
                        && precedence.get(token) <= precedence.get(stack.peek())) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString().trim();
    }
}
