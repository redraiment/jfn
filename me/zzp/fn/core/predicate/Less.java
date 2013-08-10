package me.zzp.fn.core.predicate;

import me.zzp.fn.lambda.Predicate;

public class Less implements Predicate {
    private Cmp cmp;

    public Less() {
        cmp = new Cmp();
    }

    @Override
    public Boolean call(Object... args) {
        return cmp.call(args) < 0;
    }
}
