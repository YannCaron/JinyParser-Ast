/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast.acceptance;

import java.util.function.Consumer;
import net.oolang.ast.Ast;
import net.oolang.ast.AstDefault;
import net.oolang.ast.AstType;
import net.oolang.ast.Context;
import net.oolang.ast.ExecutionEntryPoint;
import net.oolang.ast.Symbol;
import net.oolang.poc.Main;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cyann
 */
public class Visitor {

    private static class PrintContext extends Context {

        private final StringBuilder builder = new StringBuilder();

        public StringBuilder getBuilder() {
            return builder;
        }

        @Override
        public String toString() {
            return builder.toString();
        }

    }

    private static final AstType NUMBER = new AstType("Number");
    private static final AstType OPERATION = new AstType("Addition");
    private Ast ast;

    public Visitor() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Ast left = new AstDefault(NUMBER, new Symbol("1"));
        Ast right = new AstDefault(NUMBER, new Symbol("7"));
        Ast addition = new AstDefault(OPERATION, new Symbol("+"), left, right);

        Ast subRight = new AstDefault(NUMBER, new Symbol("8"));
        ast = new AstDefault(OPERATION, new Symbol("-"), addition, subRight);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getContext method, of class Visitor.
     */
    @Test
    public void testPrintVisitor() {

        net.oolang.ast.Visitor<PrintContext> printVisitor = new net.oolang.ast.Visitor<PrintContext>() {
            @Override
            public PrintContext instancingContext() {
                return new PrintContext();
            }

            @Override
            public void registerVisitors() {
                registerDefault(NUMBER, (AstDefault ast) -> {
                    getContext().getBuilder().append(ast.getSymbol().getValue());
                });

                registerDefault(OPERATION, (AstDefault ast) -> {
                    ast.visiteChild(0);
                    getContext().getBuilder().append(' ');
                    getContext().getBuilder().append(ast.getSymbol().getValue());
                    getContext().getBuilder().append(' ');
                    ast.visiteChild(1);
                });
            }
        };

        ExecutionEntryPoint<PrintContext> ep = new ExecutionEntryPoint<>(printVisitor, ast);

        assertEquals("1 + 7 - 8", ep.execute().getContext().toString());

    }

    @Test
    public void testContextInitialization() {

        net.oolang.ast.Visitor<PrintContext> printVisitor = new net.oolang.ast.Visitor<PrintContext>() {
            @Override
            public PrintContext instancingContext() {
                return new PrintContext();
            }

            @Override
            public void registerVisitors() {
                registerDefault(NUMBER, (AstDefault ast) -> {
                    getContext().getBuilder().append('N');
                });

                registerDefault(OPERATION, (AstDefault ast) -> {
                    ast.visiteChild(0);
                    getContext().getBuilder().append(" O ");
                    ast.visiteChild(1);
                });
            }
        };

        ExecutionEntryPoint<PrintContext> ep = new ExecutionEntryPoint<>(printVisitor, ast);
        assertEquals("N O N O N", ep.execute().getContext().toString());

        assertEquals("N O N O N", ep.execute().getContext().toString());

        assertEquals("N O N O N", ep.execute().getContext().toString());

    }

    @Test
    public void testLispVisitor() {
        net.oolang.ast.Visitor<PrintContext> printVisitor = new net.oolang.ast.Visitor<PrintContext>() {
            @Override
            public PrintContext instancingContext() {
                return new PrintContext();
            }

            @Override
            public void registerVisitors() {
                registerDefault(NUMBER, (AstDefault ast) -> {
                    getContext().getBuilder().append(ast.getSymbol().getValue());
                });

                registerDefault(OPERATION, (AstDefault ast) -> {
                    getContext().getBuilder().append('(');
                    ast.visiteChild(0);
                    getContext().getBuilder().append(ast.getSymbol().getValue());
                    ast.visiteChild(1);
                    getContext().getBuilder().append(')');
                });
            }
        };

        ExecutionEntryPoint<PrintContext> ep = new ExecutionEntryPoint<>(printVisitor, ast);

        assertEquals("((1+7)-8)", ep.execute().getContext().toString());
    }

}
