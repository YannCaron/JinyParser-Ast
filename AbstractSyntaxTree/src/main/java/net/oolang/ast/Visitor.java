/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Visitor<C extends Context> {

    private C context;
    private Map<AstType, Consumer<Ast>> reg;

    public C getContext() {
        return context;
    }

    public Visitor() {
    }

    void initialize() {
        context = instancingContext();
    }

    private void lazyInitialize() {
        if (reg == null) {
            reg = new HashMap<>();
            registerVisitors();
        }
    }

    public Consumer<Ast> getVisitor(AstType astType) {
        lazyInitialize();

        if (!reg.containsKey(astType)) {
            throw new AstException("Ast of type [" + astType.getName() + "] is not covered by a visitor !");
        }
        return reg.get(astType);
    }

    public abstract C instancingContext();

    protected abstract void registerVisitors();

    protected void register(AstType astType, Consumer<Ast> visitor) {
        reg.put(astType, visitor);
    }

}
