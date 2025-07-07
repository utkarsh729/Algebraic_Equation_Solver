package com.example.algebraic_equation_solver.storage;

import com.example.algebraic_equation_solver.model.Equation;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryEquationStorage {

    private final ConcurrentHashMap<String, Equation> storage = new ConcurrentHashMap<>();
    private int counter = 1;

    public Equation save(String infix, String postfix, Equation equation) {
        String id = String.valueOf(counter++);
        equation.setId(id);
        storage.put(id, equation);
        return equation;
    }

    public List<Equation> findAll() {
        return storage.values().stream().collect(Collectors.toList());
    }

    public Equation findById(String id) {
        return storage.get(id);
    }
}

