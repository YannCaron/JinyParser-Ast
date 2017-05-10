/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.poc.asm;

import org.objectweb.asm.MethodVisitor;

/**
 *
 * @author cyann
 */
public class CompilationContext {

    MethodVisitor mv;
    int currentStore;

    public CompilationContext(MethodVisitor mv) {
        this.mv = mv;
        currentStore = 0;
    }

}
