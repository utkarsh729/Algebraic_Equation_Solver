package com.example.algebraic_equation_solver.util;


import com.example.algebraic_equation_solver.model.Node;

import java.util.Stack;

public class PostfixTreeBuilder {

    public static Node build(String postfix) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("[a-zA-Z0-9]+")) {
                stack.push(new Node(token, null, null));
            } else {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new Node(token, left, right));
            }
        }
        return stack.pop();
    }
}
