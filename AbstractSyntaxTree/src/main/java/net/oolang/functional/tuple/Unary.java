/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oolang.functional.tuple;

import java.util.Objects;

/**
 *
 * @author cyann
 */
public class Unary<T1> {

    protected final T1 value1;

    public Unary(T1 value1) {
        this.value1 = value1;
    }

    public T1 getValue1() {
        return value1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.value1);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unary<?> other = (Unary<?>) obj;
        if (!Objects.equals(this.value1, other.value1)) {
            return false;
        }
        return true;
    }

    
    
}
