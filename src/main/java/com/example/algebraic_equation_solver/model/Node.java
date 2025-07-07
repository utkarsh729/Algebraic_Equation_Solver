package com.example.algebraic_equation_solver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {
    private String value;
    private Node left;
    private Node right;
}
