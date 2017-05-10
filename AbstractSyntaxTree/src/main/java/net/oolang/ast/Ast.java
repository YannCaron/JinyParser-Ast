/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Ast {

    protected Consumer<Ast> visitor;
    private final List<Ast> children;
    private final Symbol symbol;
    private final AstType type;

    public Ast(AstType type, Symbol symbol, Ast... ch) {
        this.type = type;
        this.symbol = symbol;
        this.children = Arrays.asList(ch);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public AstType getType() {
        return type;
    }

    public void visiteChild(int currentIndex) {
        if (currentIndex >= children.size()) {
            throw new AstException("Child " + currentIndex + " does not exists in children list of the AST !");
        }
        
        children.get(currentIndex).visite();
        currentIndex++;
    }
    
    public void addChild(Ast child) {
        children.add(child);
    }

    // visitor part
    void acceptVisitor(Visitor<? extends Context> visitor) {
        this.visitor = visitor.getVisitor(type);

        if (children != null) {
            for (Ast child : children) {
                child.acceptVisitor(visitor);
            }
        }
    }

    void visite() {
        visitor.accept(this);
    }

}
