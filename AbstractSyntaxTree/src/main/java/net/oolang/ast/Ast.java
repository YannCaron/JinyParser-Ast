/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

import java.util.function.Consumer;

public abstract class Ast<A extends Ast> {

    private final AstType type;
    private Consumer<A> visitor;
    private final Symbol symbol;

    public Ast(AstType type, Symbol symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public AstType getType() {
        return type;
    }

    public Consumer<A> getVisitor() {
        return visitor;
    }

    // visitor part
    protected void acceptVisitor(Visitor<? extends Context> visitor) {
        this.visitor = visitor.getVisitor(getAstClass(), type);
    }

    protected abstract Class<A> getAstClass();

    protected void visite() {
        getVisitor().accept((A) this);
    }

}
