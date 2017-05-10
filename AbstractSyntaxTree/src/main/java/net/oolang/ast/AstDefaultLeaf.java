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
public class AstDefaultLeaf extends Ast<AstDefaultLeaf> {

    public AstDefaultLeaf(AstType type, Symbol symbol) {
        super(type, symbol);
    }

    @Override
    protected Class<AstDefaultLeaf> getAstClass() {
        return AstDefaultLeaf.class;
    }

}
