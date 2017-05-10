/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.poc.asm;

import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.ISTORE;

/**
 *
 * @author cyann
 */
public class Add extends Expression {

    private Expression left, right;

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void compile(CompilationContext c) {
        right.compile(c);
        left.compile(c);

        c.mv.visitVarInsn(ILOAD, c.currentStore--);
        c.mv.visitVarInsn(ILOAD, c.currentStore--);
        c.mv.visitInsn(IADD);

        c.currentStore++;
        c.mv.visitVarInsn(ISTORE, c.currentStore);
    }

    @Override
    public int evalMaxStack(int max) {
        max = this.left.evalMaxStack(max);
        max = this.right.evalMaxStack(max);
        return max;
    }

    @Override
    public int evalMaxLocalVariables(int max) {
        max = this.left.evalMaxLocalVariables(max);
        max = this.right.evalMaxLocalVariables(max);
        return max;
    }

}
