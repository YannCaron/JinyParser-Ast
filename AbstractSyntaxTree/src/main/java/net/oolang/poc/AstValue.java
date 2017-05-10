/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.poc;

import net.oolang.ast.Ast;
import net.oolang.ast.AstType;
import net.oolang.ast.Symbol;

/**
 *
 * @author cyann
 */
public class AstValue extends Ast<AstValue> {

    public AstValue(AstType type, Symbol symbol) {
        super(type, symbol);
    }

    public String getValue() {
        return getSymbol().getValue();
    }

    @Override
    protected Class<AstValue> getAstClass() {
        return AstValue.class;
    }

}
