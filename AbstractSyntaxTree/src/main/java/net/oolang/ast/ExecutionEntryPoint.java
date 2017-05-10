/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

public class ExecutionEntryPoint<C extends Context> {

    private final Visitor<C> visitor;
    private final Ast entryPoint;

    public ExecutionEntryPoint(Visitor<C> visitor, Ast entryPoint) {
        this.visitor = visitor;
        this.entryPoint = entryPoint;
        this.entryPoint.acceptVisitor(visitor);
    }

    public C getContext() {
        return visitor.getContext();
    }
    
    public ExecutionEntryPoint<C> execute() {
        visitor.initialize();
        entryPoint.visite();
        return this;
    }

}
