/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

/**
 *
 * @author cyann
 */
public class AstType {

    private static int instanceCount = 0;
    private final int instanceNumber;

    private final String name;

    public AstType(String name) {
        this.name = name;
        instanceNumber = instanceCount++;
    }

    public String getName() {
        return name;
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }

}
