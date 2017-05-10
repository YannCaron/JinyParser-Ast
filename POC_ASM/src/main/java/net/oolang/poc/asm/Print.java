/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.poc.asm;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 *
 * @author cyann
 */
public class Print extends AST {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void compile(CompilationContext c) {
        expression.compile(c);

        c.mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        c.mv.visitVarInsn(ILOAD, c.currentStore--);
        c.mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }

    @Override
    public int evalMaxStack(int max) {
        return expression.evalMaxLocalVariables(max);
    }

        @Override
    public int evalMaxLocalVariables(int max) {
        return expression.evalMaxStack(max) + 1;
    }
   
    
}
