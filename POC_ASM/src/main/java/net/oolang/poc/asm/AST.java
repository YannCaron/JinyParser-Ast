/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.poc.asm;

/**
 *
 * @author cyann
 */
public abstract class AST {

    public abstract void compile(CompilationContext c);

    public int evalMaxStack(int max) {
        return max;
    }

    public int evalMaxLocalVariables(int max) {
        return max;
    }

}
