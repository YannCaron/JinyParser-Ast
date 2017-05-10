/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.poc.asm;

import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.ISTORE;

/**
 *
 * @author cyann
 */
public class Number extends Expression {

    private int value;

    public int getValue() {
        return value;
    }

    public Number(int value) {
        this.value = value;
    }

    @Override
    public void compile(CompilationContext c) {
        c.currentStore++;
        c.mv.visitIntInsn(BIPUSH, value);
        c.mv.visitVarInsn(ISTORE, c.currentStore);
    }

    @Override
    public int evalMaxStack(int max) {
        return max + 1;
    }

    @Override
    public int evalMaxLocalVariables(int max) {
        return max + 1;
    }
    
    
    
}
