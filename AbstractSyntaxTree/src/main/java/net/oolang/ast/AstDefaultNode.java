/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.ast;

import java.util.Arrays;
import java.util.List;

public class AstDefaultNode extends Ast<AstDefaultNode> {

    private final List<Ast> children;

    public AstDefaultNode(AstType type, Symbol symbol, Ast... ch) {
        super(type, symbol);
        this.children = Arrays.asList(ch);
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

    @Override
    protected void acceptVisitor(Visitor<? extends Context> visitor) {
        super.acceptVisitor(visitor);

        for (Ast child : children) {
            child.acceptVisitor(visitor);
        }
    }

    @Override
    protected Class<AstDefaultNode> getAstClass() {
        return AstDefaultNode.class;
    }

}
