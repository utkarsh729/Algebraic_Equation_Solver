package com.example.algebraic_equation_solver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equation {
    private String id;
    private String infix;
    private String postfix;
    private Node expressionTree;
}
