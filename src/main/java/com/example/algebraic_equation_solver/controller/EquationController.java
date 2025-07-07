package com.example.algebraic_equation_solver.controller;


import com.example.algebraic_equation_solver.model.Equation;
import com.example.algebraic_equation_solver.service.EquationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equations")
@RequiredArgsConstructor
public class EquationController {

    private final EquationService equationService;

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody Map<String, String> request) {
        String infix = request.get("equation");
        Equation eq = equationService.storeEquation(infix);
        return ResponseEntity.ok(Map.of(
                "message", "Equation stored successfully",
                "equationId", eq.getId()
        ));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Equation> equations = equationService.getAllEquations();
        return ResponseEntity.ok(Map.of(
                "equations", equations.stream().map(eq -> Map.of(
                        "equationId", eq.getId(),
                        "equation", eq.getInfix()
                ))
        ));
    }

    @PostMapping("/{id}/evaluate")
    public ResponseEntity<?> evaluate(@PathVariable String id,
                                      @RequestBody Map<String, Map<String, Double>> request) {
        double result = equationService.evaluateEquation(id, request.get("variables"));
        Equation eq = equationService.getAllEquations().stream()
                .filter(e -> e.getId().equals(id)).findFirst().orElseThrow();
        return ResponseEntity.ok(Map.of(
                "equationId", eq.getId(),
                "equation", eq.getInfix(),
                "variables", request.get("variables"),
                "result", result
        ));
    }
}
