package com.example.algebraic_equation_solver.service;



import com.example.algebraic_equation_solver.model.Equation;

import java.util.List;
import java.util.Map;

public interface EquationService {
    Equation storeEquation(String infix);
    List<Equation> getAllEquations();
    double evaluateEquation(String id, Map<String, Double> variables);
}
