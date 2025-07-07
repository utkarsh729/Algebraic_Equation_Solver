package com.example.algebraic_equation_solver.service;



import com.example.algebraic_equation_solver.model.Equation;
import com.example.algebraic_equation_solver.storage.InMemoryEquationStorage;
import com.example.algebraic_equation_solver.util.InfixToPostfixConverter;
import com.example.algebraic_equation_solver.util.PostfixEvaluator;
import com.example.algebraic_equation_solver.util.PostfixTreeBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EquationServiceImpl implements EquationService {

    private final InMemoryEquationStorage storage;

    @Override
    public Equation storeEquation(String infix) {
        String postfix = InfixToPostfixConverter.convert(infix);
        var tree = PostfixTreeBuilder.build(postfix);
        Equation eq = new Equation(null, infix, postfix, tree);
        return storage.save(infix, postfix, eq);
    }

    @Override
    public List<Equation> getAllEquations() {
        return storage.findAll();
    }

    @Override
    public double evaluateEquation(String id, Map<String, Double> variables) {
        Equation eq = storage.findById(id);
        if (eq == null) throw new RuntimeException("Equation not found!");
        return PostfixEvaluator.evaluate(eq.getExpressionTree(), variables);
    }
}
