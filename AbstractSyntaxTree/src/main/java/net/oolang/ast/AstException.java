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
public class AstException extends RuntimeException {

    public AstException(String message) {
        super(message);
    }

    public AstException(String message, Throwable cause) {
        super(message, cause);
    }

}
