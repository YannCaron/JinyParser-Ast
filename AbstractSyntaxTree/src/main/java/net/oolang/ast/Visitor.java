/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import net.oolang.functional.tuple.Pair;

public abstract class Visitor<C extends Context> {

    private C context;
    private Map<Pair<Class<? extends Ast>, AstType>, Consumer<? extends Ast>> reg;

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

    public <A extends Ast> Consumer<A> getVisitor(Class<A> clazz, AstType astType) {
        lazyInitialize();

        /*
        Pair<Class<? extends Ast>, AstType> key = new Pair<>(clazz, astType);

        if (!reg.containsKey(key)) {
            throw new AstException(String.format("Ast for class [%1s] of type [%2s] is not covered by a visitor !", clazz.getSimpleName(), astType.getName()));
        }
        return (Consumer<A>) reg.get(key);*/
        Consumer<A> consumer = getRecursiveVisitor(clazz, astType);
        if (consumer == null) {
            throw new AstException(String.format("Ast for class [%1s] of type [%2s] is not covered by a visitor !", clazz.getSimpleName(), astType.getName()));
        }
        return consumer;
    }

    private <A extends Ast> Consumer<A> getRecursiveVisitor(Class<? extends Ast> clazz, AstType astType) {
        Pair<Class<? extends Ast>, AstType> key = new Pair<>(clazz, astType);
        if (reg.containsKey(key)) {
            return (Consumer<A>) reg.get(key);
        }
        if (!clazz.getSuperclass().equals(Object.class)) {
            return getRecursiveVisitor((Class<? extends Ast>) clazz.getSuperclass(), astType);
        }
        return null;
    }

    protected abstract C instancingContext();

    protected abstract void registerVisitors();

    protected <A extends Ast> void register(Class<A> clazz, AstType astType, Consumer<A> visitor) {
        reg.put(new Pair<>(clazz, astType), visitor);
    }

    protected void register(AstType astType, Consumer<Ast> visitor) {
        reg.put(new Pair<>(Ast.class, astType), visitor);
    }

    protected void registerDefault(AstType astType, Consumer<AstDefault> visitor) {
        reg.put(new Pair<>(AstDefault.class, astType), visitor);
    }

}
