package com.example.algebraic_equation_solver.util;

import com.example.algebraic_equation_solver.model.Node;

import java.util.Map;


import java.util.Map;

public class PostfixEvaluator {

    public static double evaluate(Node node, Map<String, Double> variables) {
        if (node == null) return 0;

        if (node.getLeft() == null && node.getRight() == null) {
            String val = node.getValue();
            if (val.matches("[a-zA-Z]+")) {
                if (!variables.containsKey(val)) {
                    throw new IllegalArgumentException("Missing value for variable: " + val);
                }
                return variables.get(val);
            } else {
                return Double.parseDouble(val);
            }
        }

        double left = evaluate(node.getLeft(), variables);
        double right = evaluate(node.getRight(), variables);

        return switch (node.getValue()) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            case "^" -> Math.pow(left, right);
            default -> throw new IllegalArgumentException("Unknown operator: " + node.getValue());
        };
    }
}
