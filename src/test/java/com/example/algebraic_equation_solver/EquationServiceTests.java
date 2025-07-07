package com.example.algebraic_equation_solver;

import com.example.algebraic_equation_solver.model.Equation;
import com.example.algebraic_equation_solver.service.EquationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EquationServiceTests {

    @Autowired
    private EquationService equationService;

    @Test
    void storeSimpleEquation() {
        Equation eq = equationService.storeEquation("3 * x + 2");
        assertNotNull(eq);
        assertEquals("3 * x + 2", eq.getInfix());
        assertNotNull(eq.getPostfix());
        assertNotNull(eq.getExpressionTree());
    }

    @Test
    void retrieveStoredEquations() {
        equationService.storeEquation("x + y");
        equationService.storeEquation("x - y");
        List<Equation> equations = equationService.getAllEquations();
        assertTrue(equations.size() >= 2);
    }

    @Test
    void evaluateSimpleEquation() {
        Equation eq = equationService.storeEquation("2 * x + 3");
        double result = equationService.evaluateEquation(eq.getId(), Map.of("x", 4.0));
        assertEquals(11.0, result);
    }

    @Test
    void evaluateComplexEquation() {
        Equation eq = equationService.storeEquation("(x + y) * (x - y)");
        double result = equationService.evaluateEquation(eq.getId(), Map.of("x", 5.0, "y", 3.0));
        assertEquals((5 + 3) * (5 - 3), result);
    }

    @Test
    void evaluateWithPowerOperator() {
        Equation eq = equationService.storeEquation("x ^ 2 + y ^ 2");
        double result = equationService.evaluateEquation(eq.getId(), Map.of("x", 3.0, "y", 4.0));
        assertEquals(25.0, result);
    }

    @Test
    void failOnInvalidSyntax() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                equationService.storeEquation("3 * + x"));
        assertTrue(ex.getMessage().contains("Invalid syntax"));
    }

    @Test
    void failOnMismatchedParentheses() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                equationService.storeEquation("(x + y"));
        assertTrue(ex.getMessage().contains("Mismatched parentheses"));
    }

    @Test
    void failOnEndingWithOperator() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                equationService.storeEquation("x + y -"));
        assertTrue(ex.getMessage().contains("Equation cannot end with operator"));
    }

    @Test
    void failWhenVariableMissing() {
        Equation eq = equationService.storeEquation("x + y");
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                equationService.evaluateEquation(eq.getId(), Map.of("x", 2.0)));
        assertTrue(ex.getMessage().contains("Missing value for variable"));
    }

    @Test
    void failOnUnknownId() {
        Exception ex = assertThrows(RuntimeException.class, () ->
                equationService.evaluateEquation("9999", Map.of("x", 1.0)));
        assertTrue(ex.getMessage().contains("not found"));
    }
}
