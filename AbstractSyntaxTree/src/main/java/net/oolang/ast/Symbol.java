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
public class Symbol {
    
    private final String value;

    public Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
