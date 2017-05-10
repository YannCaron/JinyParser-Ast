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
public class Pair<T1, T2> extends Unary<T1> {
    
    private final T2 value2;

    public Pair(T1 value1, T2 value2) {
        super(value1);
        this.value2 = value2;
    }

    public T2 getValue2() {
        return value2;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.value2);
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
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (!Objects.equals(this.getValue1(), other.getValue1())) {
            return false;
        }
        if (!Objects.equals(this.value2, other.value2)) {
            return false;
        }
        return true;
    }
    
    
    
}
